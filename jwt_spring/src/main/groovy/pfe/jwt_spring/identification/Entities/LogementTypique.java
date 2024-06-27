package pfe.jwt_spring.identification.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LogementTypique extends Logement {
    @Enumerated(EnumType.STRING)
    private LogementType typologie;

    public LogementTypique( int pourcentage, Qualite qualite, LogementType typologie) {
        super(typologie, pourcentage, qualite);
        this.typologie = typologie;
    }

}