package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.AccountTypeData;
import com.seminario.undeadbank.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeDataRepository extends JpaRepository<AccountTypeData, Long> {
    Optional<AccountTypeData> findByAccountTypeClassification(Classification accountTypeClassification);
}
