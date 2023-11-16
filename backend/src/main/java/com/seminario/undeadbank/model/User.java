package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "User")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long identificationNumber;
    private String fullName;
    private String username;
    private String password;

    @Column(nullable = false)
    private LocalDate birthday;

    private String email;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "userClassificationId")
    private Classification userClassification;

}
