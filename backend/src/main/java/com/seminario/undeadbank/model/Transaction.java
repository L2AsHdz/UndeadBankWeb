package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long referenceNumber;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    private Double previousBalance;
    private Double amount;
    private LocalDateTime datetime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "transactionClassificationId")
    private Classification transactionClassification;

}

