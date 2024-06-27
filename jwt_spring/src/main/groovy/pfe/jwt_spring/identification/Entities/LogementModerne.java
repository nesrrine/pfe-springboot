package pfe.jwt_spring.identification.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class LogementModerne extends Logement {
    @Enumerated(EnumType.STRING)
    private LogementType typologie;

    public LogementModerne(int pourcentage, Qualite qualite, LogementType typologie) {
        super(typologie, pourcentage, qualite);
        this.typologie = typologie;
    }}
