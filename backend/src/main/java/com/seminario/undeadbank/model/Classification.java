package com.seminario.undeadbank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Classification")
@Getter @Setter @ToString @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classificationId;

    private Integer internalId;
    private String description;

    @ManyToOne
    @JoinColumn(name = "parentClassificationId")
    @JsonBackReference
    private Classification parentClassification;

}
