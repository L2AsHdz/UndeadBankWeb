package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}