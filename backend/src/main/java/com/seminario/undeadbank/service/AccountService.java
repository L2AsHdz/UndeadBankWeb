package com.seminario.undeadbank.service;

import com.seminario.undeadbank.dto.ValidateRequestDto;
import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.Account;
import com.seminario.undeadbank.model.AccountDetailView;
import com.seminario.undeadbank.model.AccountLog;
import com.seminario.undeadbank.model.CustomerAccount;
import com.seminario.undeadbank.repository.*;
import com.seminario.undeadbank.utils.ClassificationEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserService userService;
    private final AccountRepository accountRepository;
    private final AccountLogRepository accountLogRepository;
    private final CustomerAccountRepository customerAccountRepository;
    private final AccountDetailRepository accountDetailRepository;
    private final ClassificationRepository classificationRepository;

    public boolean validate(ValidateRequestDto requestDto) {
        return accountRepository.existsByAccountIdAndAssociationPin(requestDto.account(), requestDto.associationPin());
    }

    public boolean existsByAccountId(Long accountId) {
        return accountRepository.existsById(accountId);
    }

    public Account findByAccountId(Long accountId) {
        var oAccount = accountRepository.findById(accountId);

        if (oAccount.isPresent()) {
            return oAccount.get();
        }

        throw new BankException("La cuenta no existe")
                .withStatus(HttpStatus.NOT_FOUND)
                .withTitle("Cuenta no encontrada");
    }

    public List<AccountDetailView> findAccountsByUserId(Long userId) {
        return accountDetailRepository.findByUserId(userId);
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public Account save(Long userId, Account account) {
        var user = userService.findById(userId);

        if (user == null) throw new BankException("User not found");

        switch (account.getAccountClassification().getClassificationId().intValue()) {
            case 8 -> account.setNameAccount(user.getUsername()+".basic");
            case 9 -> account.setNameAccount(user.getUsername()+".premium");
            case 10 -> account.setNameAccount(user.getUsername()+".plus");
        }

        var createdAccount =  accountRepository.save(account);
        customerAccountRepository.save(CustomerAccount.builder()
                .account(createdAccount)
                .user(user)
                .build());

        accountLogRepository.save(AccountLog.builder()
                        .message("Se ha creado la cuenta " + createdAccount.getNameAccount())
                        .datetime(LocalDateTime.now())
                        .user(user)
                        .account(createdAccount)
                        .operationClassification(classificationRepository.findByInternalId(ClassificationEnum.CREATE.getId()))
                .build()
        );

        return createdAccount;
    }

    public Account update(Long id, Account account) {
        var oAccount = accountRepository.findById(id);

        if (oAccount.isEmpty()) throw new BankException("Account not found");

        return accountRepository.save(account);
    }

    public void deleteByAccoutId(Long accountId) {
        var oAccount = accountRepository.findById(accountId);

        if (oAccount.isEmpty()) throw new BankException("Account not found");

        var account = oAccount.get();
        account.setStatusClassification(classificationRepository.findByInternalId(ClassificationEnum.INACTIVE.getId()));

        accountLogRepository.save(AccountLog.builder()
                        .message("Se ha eliminado la cuenta " +account.getNameAccount())
                        .datetime(LocalDateTime.now())
                        .user(
                                customerAccountRepository.findByAccount(account).getUser()
                        )
                        .account(account)
                        .operationClassification(classificationRepository.findByInternalId(ClassificationEnum.DELETE.getId()))
                        .build()
        );
        accountRepository.save(account);
    }

    public List<AccountDetailView> findAll() {
        return accountDetailRepository.findAll();
    }

    public boolean validateBalance(Double balance, Long accountId) {
        return accountRepository.existsByBalanceIsGreaterThanEqualAndAccountId(balance, accountId);
    }

    public void saveAll(Iterable<Account> accounts) {
        accountRepository.saveAll(accounts);
    }
}
