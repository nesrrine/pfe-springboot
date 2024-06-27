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
public class ProgrammeIntervention {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantites;
    private Double cout;
    @ManyToOne
    @JoinColumn(name = "infrastructurestype_id") // Change the column name as per convention
    @JsonBackReference
    private Infrastructurestype infrastructurestype;
}
