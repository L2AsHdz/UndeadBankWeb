package com.seminario.undeadbank.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Parameter")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parameterId;

    private String name;

    @Lob
    private String value;

}
