package pfe.jwt_spring.identification.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.DonneeGenerale;
import pfe.jwt_spring.identification.repository.DonneesGeneralesRepository;

@Service
public class DonneesGeneralesService {

    @Autowired
    private DonneesGeneralesRepository donneesGeneralesRepository;

    public DonneeGenerale saveDonneeasGenerales(DonneeGenerale donneesGenerales) {
        // Calculer la densité avant de sauvegarder les données générales
        donneesGenerales.calculerDensite();
        return donneesGeneralesRepository.save(donneesGenerales);
    }

    public DonneeGenerale updateDonneesGenerales(Long id, DonneeGenerale donneesGeneralesDetails) {
        DonneeGenerale donneesGenerales = donneesGeneralesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Données générales introuvables avec l'ID: " + id));

        // Mise à jour des propriétés nécessaires
        donneesGenerales.setSurface(donneesGeneralesDetails.getSurface());
        donneesGenerales.setSurfaceUrbanisee(donneesGeneralesDetails.getSurfaceUrbanisee());
        donneesGenerales.setNombreLogements(donneesGeneralesDetails.getNombreLogements());

        // Recalculer la densité après la mise à jour
        donneesGenerales.calculerDensite();

        return donneesGeneralesRepository.save(donneesGenerales);
    }

    public void deleteDonneesGenerales(Long id) {
        donneesGeneralesRepository.deleteById(id);
    }
}
