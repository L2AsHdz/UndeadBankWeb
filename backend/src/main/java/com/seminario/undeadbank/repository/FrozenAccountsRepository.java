package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.FrozenAccountsView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrozenAccountsRepository extends JpaRepository<FrozenAccountsView, Long> {
}
