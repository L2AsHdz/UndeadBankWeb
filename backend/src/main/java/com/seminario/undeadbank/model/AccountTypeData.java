package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "AccountTypeData")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTypeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountTypeDataId;

    private String currency;
    private Double initialBalance;
    private Double exchangeRate;

    @ManyToOne
    @JoinColumn(name = "accountTypeClassificationId")
    private Classification accountTypeClassification;

}

