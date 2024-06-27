package pfe.jwt_spring.identification.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Infrastructurestype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @OneToMany(mappedBy = "infrastructurestype", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ProgrammeIntervention> infrastructurestypeList;
}
