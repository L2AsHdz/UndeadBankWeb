package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    boolean existsByAccountIdAndAssociationPin(Long accountId, int associationPin);
    boolean existsByBalanceIsGreaterThanEqualAndAccountId(Double balance, Long accountId);
}
