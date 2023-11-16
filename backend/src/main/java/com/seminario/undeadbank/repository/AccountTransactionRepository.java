package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.AccountTransactionsView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountTransactionRepository extends JpaRepository<AccountTransactionsView, Long> {
    List<AccountTransactionsView> findByUserIdAndAccountIdAndOperationDateBetween(
            Long userId, Long accountId, LocalDateTime startDate, LocalDateTime endDate);
    List<AccountTransactionsView> findByOperationDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
