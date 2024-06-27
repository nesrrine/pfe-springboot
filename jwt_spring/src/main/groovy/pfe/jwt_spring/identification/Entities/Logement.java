package pfe.jwt_spring.identification.Entities;


import jakarta.persistence.*;
import lombok.*;

import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Logement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LogementType type;

    private int pourcentage;

    @Enumerated(EnumType.STRING)
    private Qualite qualite;

    // Constructeur avec paramètres
    public Logement(LogementType type, int pourcentage, Qualite qualite) {
        this.type = type;
        this.pourcentage = pourcentage;
        this.qualite = qualite;
    }
}