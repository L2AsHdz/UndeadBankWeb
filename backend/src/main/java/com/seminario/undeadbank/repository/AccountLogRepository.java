package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.AccountLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountLogRepository extends JpaRepository<AccountLog, Long> {
}
