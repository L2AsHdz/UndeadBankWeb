package com.seminario.undeadbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AccountDetailView")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailView {

    @Id
    private Long accountId;
    private Long userId;
    private String userFullName;
    private String nameAccount;
    private Double balance;
    private String accountType;
    private String accountStatus;
    private LocalDateTime creationDate;
}
