package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "AccountLog")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Lob
    private String message;

    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "operationClassificationId")
    private Classification operationClassification;

}

