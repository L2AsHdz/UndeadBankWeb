package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.AccountDetailView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountDetailRepository extends JpaRepository<AccountDetailView, Long> {
    Optional<AccountDetailView> findByAccountId(Long accountId);
    List<AccountDetailView> findByUserId(Long userId);
}
