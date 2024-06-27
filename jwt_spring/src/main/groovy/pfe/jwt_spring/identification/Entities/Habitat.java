package pfe.jwt_spring.identification.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Habitat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantites;
    private double cout;

    @ManyToOne
    @JoinColumn(name = "Equipement_Publics_id") // Column name can be adjusted as per your convention
    @JsonBackReference
    private Equipement_Publics_type equipement_Publics_type; // Changed the variable name to match Equipement_Publics_type
}
