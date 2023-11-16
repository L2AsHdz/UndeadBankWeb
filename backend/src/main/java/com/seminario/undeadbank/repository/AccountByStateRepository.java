package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.AccountsByStateView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountByStateRepository extends JpaRepository<AccountsByStateView, Long> {
}
