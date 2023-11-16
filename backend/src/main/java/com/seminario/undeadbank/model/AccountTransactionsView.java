package com.seminario.undeadbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AccountTransactionsView")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionsView {

    @Id
    private Long transactionId;

    private Long referenceNumber;
    private Long userId;
    private String userFullName;
    private Long accountId;
    private String nameAccount;
    private Double previousBalance;
    private Double amount;
    private LocalDateTime operationDate;
    private String operationType;
}