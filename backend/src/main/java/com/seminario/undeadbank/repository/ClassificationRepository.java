package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findByInternalId(Long internalId);
}
