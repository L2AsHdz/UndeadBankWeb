package com.seminario.undeadbank.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "FrozenAccountsView")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class FrozenAccountsView {

    @Id
    private Long accountId;
    private Long userId;
    private String userFullName;
    private String nameAccount;
    private Double balance;
    private String accountType;
}
