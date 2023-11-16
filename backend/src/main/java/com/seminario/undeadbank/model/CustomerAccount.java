package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CustomerAccount")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerAccountId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

}

