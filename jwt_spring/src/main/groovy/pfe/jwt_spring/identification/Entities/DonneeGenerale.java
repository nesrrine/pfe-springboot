package pfe.jwt_spring.identification.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DonneeGenerale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double surface;
    private Double surfaceUrbanisee;
    private Integer nombreLogements;
    private Double densite;


    // Méthode pour calculer la densité
    public void calculerDensite() {
        // Assurez-vous de gérer le cas où la surface urbanisée est nulle ou égale à zéro pour éviter une division par zéro
        if (surfaceUrbanisee != null && surfaceUrbanisee != 0) {
            densite = (double) nombreLogements / surfaceUrbanisee;
        } else {
            // Gérer le cas où la surface urbanisée est nulle ou égale à zéro
            densite = 0.0; // ou lancez une exception ou gérez de manière appropriée selon votre cas d'utilisation
        }
    }
}
