package com.seminario.undeadbank.service;

import com.seminario.undeadbank.dto.TransactionDetailsRequestDto;
import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.AccountDetailView;
import com.seminario.undeadbank.model.AccountTransactionsView;
import com.seminario.undeadbank.model.AccountsByStateView;
import com.seminario.undeadbank.model.FrozenAccountsView;
import com.seminario.undeadbank.repository.AccountByStateRepository;
import com.seminario.undeadbank.repository.AccountDetailRepository;
import com.seminario.undeadbank.repository.AccountTransactionRepository;
import com.seminario.undeadbank.repository.FrozenAccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final AccountTransactionRepository accountTransactionRepository;
    private final FrozenAccountsRepository frozenAccountsRepository;
    private final AccountDetailRepository accountDetailRepository;
    private final AccountByStateRepository accountByStateRepository;

    public List<AccountTransactionsView> getUserTransactions(TransactionDetailsRequestDto requestDto) {
        return accountTransactionRepository.findByUserIdAndAccountIdAndOperationDateBetween(
                requestDto.userId(),
                requestDto.accountId(),
                LocalDateTime.parse(requestDto.startDate(), DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse(requestDto.endDate(), DateTimeFormatter.ISO_DATE_TIME)
        );
    }

    public List<AccountTransactionsView> getTransactions(TransactionDetailsRequestDto requestDto) {
        return accountTransactionRepository.findByOperationDateBetween(
                LocalDateTime.parse(requestDto.startDate(), DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse(requestDto.endDate(), DateTimeFormatter.ISO_DATE_TIME)
        );
    }

    public List<FrozenAccountsView> getFrozenAccounts() {
        return frozenAccountsRepository.findAll();
    }

    public AccountDetailView getAccountDetail(Long accountId) {
        var oAccountDetail = accountDetailRepository.findByAccountId(accountId);

        if (oAccountDetail.isEmpty()) throw new BankException("Account not found");

        return oAccountDetail.get();
    }

    public AccountsByStateView getAccountsByState() {
        return accountByStateRepository.findAll().get(0);
    }
}
