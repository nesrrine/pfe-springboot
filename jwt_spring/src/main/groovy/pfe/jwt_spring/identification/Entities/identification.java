package pfe.jwt_spring.identification.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class identification {  @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

    @OneToOne
    private Commune commune;

    @OneToOne
    private CouvertureReseaux couvertureReseaux;

    @OneToOne
    private Delegation delegation;

    @OneToOne
    private DonneeGenerale donneeGenerale;

    @OneToOne
    private Equipement_Publics_type equipementPublicsType;

    @OneToOne
    private Gouvernorat gouvernorat;

    @OneToOne
    private Habitat habitat;

    @OneToOne
    private Infrastructurestype infrastructuresType;

    @OneToOne
    private Logement logement;

    @OneToOne
    private LogementModerne logementModerne;

    @OneToOne
    private LogementTypique logementTypique;

    @OneToOne
    private ProgrammeIntervention programmeIntervention;

    @OneToOne
    private Quartier quartier;

    @OneToOne
    private TypeReseau typeReseau;}


