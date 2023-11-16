package com.seminario.undeadbank.service;

import com.seminario.undeadbank.dto.TransactionRequestDto;
import com.seminario.undeadbank.dto.TransactionResponseDto;
import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.AccountTypeData;
import com.seminario.undeadbank.model.Transaction;
import com.seminario.undeadbank.repository.ClassificationRepository;
import com.seminario.undeadbank.repository.ParameterRepository;
import com.seminario.undeadbank.repository.TransactionRepository;
import com.seminario.undeadbank.utils.ClassificationEnum;
import com.seminario.undeadbank.utils.ResponseState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AccountService accountService;
    private final AccountTypeService accountTypeService;
    private final ParameterRepository parameterRepository;
    private final TransactionRepository transactionRepository;
    private final ClassificationRepository classificationRepository;

    @Transactional
    public TransactionResponseDto doTransaction(TransactionRequestDto transactionDto) {
        var allAccountsExists = accountService.existsByAccountId(transactionDto.sourceAccount()) &&
                accountService.existsByAccountId(transactionDto.destinationAccount()) &&
                accountService.existsByAccountId(transactionDto.paymentAccount());
        if (!allAccountsExists)
            return new TransactionResponseDto(0L,
                    ResponseState.ACCOUNT_NOT_FOUND.getCode(), "Alguna de las cuentas no existe");

        var sourceAccount = accountService.findByAccountId(transactionDto.sourceAccount());
        var destinationAccount = accountService.findByAccountId(transactionDto.destinationAccount());
        var paymentAccount = accountService.findByAccountId(transactionDto.paymentAccount());

        var allAccountsAreActive =
                sourceAccount.getStatusClassification().getInternalId() == ClassificationEnum.ACTIVE.getId() &&
                destinationAccount.getStatusClassification().getInternalId() == ClassificationEnum.ACTIVE.getId() &&
                paymentAccount.getStatusClassification().getInternalId() == ClassificationEnum.ACTIVE.getId();

        if (!allAccountsAreActive) return new TransactionResponseDto(0L,
                ResponseState.BAD_REQUEST.getCode(), "Alguna de las cuentas no esta activa");


        var haveEnoughBalance = accountService.validateBalance(transactionDto.totalAmount(), transactionDto.sourceAccount());
        if (!haveEnoughBalance)
            return new TransactionResponseDto(0L,
                    ResponseState.INSUFFICIENT_MONEY.getCode(), "No hay suficiente saldo en la cuenta origen");

        var referenceNumber = parameterRepository.findReferenceNumberValueByName("transaction.referenceNumber")
                .orElseThrow(() -> new BankException("No se pudo obtener el numero de referencia"));

        var transactionSource = Transaction.builder()
                .referenceNumber(referenceNumber)
                .account(sourceAccount)
                .previousBalance(sourceAccount.getBalance())
                .amount(
                        transactionDto.amount() / accountTypeService.getExchangeRate(sourceAccount)
                )
                .datetime(LocalDateTime.now())
                .transactionClassification(
                        classificationRepository.findByInternalId(ClassificationEnum.DEBIT.getId())
                )
                .description("Transferencia a cuenta " + transactionDto.destinationAccount())
                .build();
        var transactionDestination = Transaction.builder()
                .referenceNumber(referenceNumber)
                .account(destinationAccount)
                .previousBalance(destinationAccount.getBalance())
                .amount(
                        transactionDto.amount() / accountTypeService.getExchangeRate(destinationAccount)
                )
                .datetime(LocalDateTime.now())
                .transactionClassification(
                        classificationRepository.findByInternalId(ClassificationEnum.CREDIT.getId())
                )
                .description("Transferencia de cuenta " + transactionDto.sourceAccount())
                .build();
        sourceAccount.setBalance(sourceAccount.getBalance() - transactionDto.amount());
        destinationAccount.setBalance(destinationAccount.getBalance() + transactionDto.amount());
        accountService.saveAll(List.of(sourceAccount, destinationAccount));
        transactionRepository.saveAll(List.of(transactionSource, transactionDestination));

        var transactionSourceCommission = Transaction.builder()
                .referenceNumber(referenceNumber)
                .account(sourceAccount)
                .previousBalance(sourceAccount.getBalance())
                .amount(
                        transactionDto.commission() / accountTypeService.getExchangeRate(sourceAccount)
                )
                .datetime(LocalDateTime.now())
                .transactionClassification(
                        classificationRepository.findByInternalId(ClassificationEnum.DEBIT.getId())
                )
                .description("Comision por transferencia a cuenta " + transactionDto.destinationAccount())
                .build();
        var transactionPayment = Transaction.builder()
                .referenceNumber(referenceNumber)
                .account(paymentAccount)
                .previousBalance(paymentAccount.getBalance())
                .amount(
                        transactionDto.commission() / accountTypeService.getExchangeRate(paymentAccount)
                )
                .datetime(LocalDateTime.now())
                .transactionClassification(
                        classificationRepository.findByInternalId(ClassificationEnum.CREDIT.getId())
                )
                .description("Comision por transferencia de cuenta " + transactionDto.sourceAccount())
                .build();
        sourceAccount.setBalance(sourceAccount.getBalance() - transactionDto.commission());
        paymentAccount.setBalance(paymentAccount.getBalance() + transactionDto.commission());
        accountService.saveAll(List.of(sourceAccount, paymentAccount));
        transactionRepository.saveAll(List.of(transactionSourceCommission, transactionPayment));

        parameterRepository.setReferenceNumberValueByName("transaction.referenceNumber", referenceNumber + 1);

        return new TransactionResponseDto(referenceNumber,
                ResponseState.SUCCESS.getCode(), "Transaccion realizada exitosamente");
    }
}
