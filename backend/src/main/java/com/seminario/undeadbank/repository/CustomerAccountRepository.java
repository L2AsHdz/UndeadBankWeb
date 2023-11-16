package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.Account;
import com.seminario.undeadbank.model.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {
    CustomerAccount findByAccount(Account account);
}
