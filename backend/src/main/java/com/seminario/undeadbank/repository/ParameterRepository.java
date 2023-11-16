package com.seminario.undeadbank.repository;

import com.seminario.undeadbank.exception.BankException;
import com.seminario.undeadbank.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    Optional<Parameter> findByName(String name);
    @Query("SELECT p.value FROM Parameter p WHERE p.name = :name")
    String findValueByName(@Param("name") String name);

    default Optional<Long> findReferenceNumberValueByName(String name) {
        String stringValue = findValueByName(name);
        return Optional.ofNullable(stringValue).map(Long::valueOf);
    }

    default void setReferenceNumberValueByName(String name, Long value) {
        var parameter = findByName(name).orElseThrow(() -> new BankException("No se pudo obtener el parametro"));
        parameter.setValue(String.valueOf(value));
        save(parameter);
    }
}
