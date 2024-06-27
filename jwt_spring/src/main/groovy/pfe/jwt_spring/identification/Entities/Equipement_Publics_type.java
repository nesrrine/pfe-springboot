package pfe.jwt_spring.identification.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipement_Publics_type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "equipement_Publics_type", cascade = CascadeType.ALL) // Corrected the mappedBy property
    @JsonManagedReference
    private Set<Habitat> habitats;
}
