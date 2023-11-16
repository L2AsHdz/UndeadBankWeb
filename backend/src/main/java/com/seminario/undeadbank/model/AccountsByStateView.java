package com.seminario.undeadbank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "AccountsByStateView")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountsByStateView {

    @Id
    private Long id;
    private Integer cuentasActivas;
    private Integer cuentasInactivas;
    private Integer cuentasCongeladas;
}
