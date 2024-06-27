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
public class CouvertureReseaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int pourcentage;

    @ManyToOne
    @JoinColumn(name = "type_reseau_id", referencedColumnName = "id")
    @JsonBackReference

    private TypeReseau typeReseau;
}
