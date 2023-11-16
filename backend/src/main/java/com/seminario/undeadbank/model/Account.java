package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Account")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String nameAccount;
    private Integer associationPin;
    private Double balance;

    @ManyToOne
    @JoinColumn(name = "accountClassificationId")
    private Classification accountClassification;

    @ManyToOne
    @JoinColumn(name = "statusClassificationId")
    private Classification statusClassification;

    private Boolean notify;

}

