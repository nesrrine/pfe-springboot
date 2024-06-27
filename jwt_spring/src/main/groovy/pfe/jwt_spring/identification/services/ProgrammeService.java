package pfe.jwt_spring.identification.services;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.*;
import pfe.jwt_spring.identification.repository.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {
    @Autowired
    private ProgrammeRepository programmeRepository;
    @Autowired

    private GouvernoratRepository gouvernoratRepository;

    @Autowired
    private DelegationRepository delegationRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private QuartierRepository quartierRepository;


    public List<Programme> getAllProgrammes() {
        return programmeRepository.findAll();
    }

    public Optional<Programme> getProgrammeById(Long id) {
        return programmeRepository.findById(id);
    }

    public Programme createProgramme(Programme programme) {
        return programmeRepository.save(programme);
    }

    public Programme updateProgramme(Long id, Programme programmeDetails) {
        Optional<Programme> optionalProgramme = programmeRepository.findById(id);
        if (optionalProgramme.isPresent()) {
            Programme programme = optionalProgramme.get();
            programme.setName(programmeDetails.getName());
            // Mettez à jour d'autres propriétés si nécessaire
            return programmeRepository.save(programme);
        } else {
            // Gérer l'erreur, par exemple, lancer une exception ou retourner null
            return null;
        }
    }
    public void deleteProgramme(Long programmeId) {
     Optional<Programme> programmeOptional = programmeRepository.findById(programmeId);
           if (programmeOptional.isPresent()) {
               programmeRepository.deleteById(programmeId);

               // Récupérer tous les programmes restants
               List<Programme> programmes = programmeRepository.findAll();

               // Renommer les IDs à partir de 1
               Long index = 1L;
               for (Programme programme : programmes) {
                   programme.setId(index++);
                   programmeRepository.save(programme);
               }
           } else {
               // Gérer le cas où le programme n'existe pas
        }
    }







    public List<Gouvernorat> getAllGouvernorats() {
        return gouvernoratRepository.findAll();
    }

    public Optional<Gouvernorat> getGouvernoratById(Long id) {
        return gouvernoratRepository.findById(id);
    }

    public Gouvernorat createGouvernorat(Gouvernorat gouvernorat) {
        return gouvernoratRepository.save(gouvernorat);
    }

    public Gouvernorat updateGouvernorat(Long id, Gouvernorat gouvernoratDetails) {
        Optional<Gouvernorat> optionalgouvernorat = gouvernoratRepository.findById(id);
        if (optionalgouvernorat.isPresent()) {
            Gouvernorat gouvernorat = optionalgouvernorat.get();
            gouvernorat.setName(gouvernoratDetails.getName());
            return gouvernoratRepository.save(gouvernorat); // Correction de la casse du nom de la variable
        } else {
            // Gérer l'erreur si le quartier n'est pas trouvé
            return null;
        }
    }


    public void deleteGouvernorat(Long id) {
        gouvernoratRepository.deleteById(id);
    }


    // Méthodes CRUD pour Delegation
    public List<Delegation> getAllDelegations() {
        return delegationRepository.findAll();
    }

    public Optional<Delegation> getDelegationById(Long id) {
        return delegationRepository.findById(id);
    }

    public Delegation createDelegation(Delegation delegation) {
        return delegationRepository.save(delegation);
    }

    // Méthode pour mettre à jour une délégation existante
    public Delegation updateDelegation(Long id, Delegation delegationDetails) {
        Optional<Delegation> optionalDelegation = delegationRepository.findById(id);
        if (optionalDelegation.isPresent()) {
            Delegation delegation = optionalDelegation.get();
            delegation.setName(delegationDetails.getName());
            // Mettez à jour d'autres propriétés si nécessaire
            return delegationRepository.save(delegation);
        } else {
            // Gérer l'erreur si la délégation n'est pas trouvée
            return null;
        }
    }

    // Méthode pour supprimer une délégation
    public void deleteDelegation(Long id) {
        delegationRepository.deleteById(id);
    }
    // choisir commune par delegation

    // Méthode pour obtenir les delegation d'un gouvernorat donné
   
    // Méthodes CRUD pour Commune
    public List<Commune> getAllCommunes() {
        return communeRepository.findAll();
    }

    public Optional<Commune> getCommuneById(Long id) {
        return communeRepository.findById(id);
    }

    public Commune createCommune(Commune commune) {
        return communeRepository.save(commune);
    }

    // Méthode pour mettre à jour une commune existante
    public Commune updateCommune(Long id, Commune communeDetails) {
        Optional<Commune> optionalCommune = communeRepository.findById(id);
        if (optionalCommune.isPresent()) {
            Commune commune = optionalCommune.get();
            commune.setName(communeDetails.getName());
            // Mettez à jour d'autres propriétés si nécessaire
            return communeRepository.save(commune);
        } else {
            // Gérer l'erreur si la commune n'est pas trouvée
            return null;
        }
    }

    // Méthode pour supprimer une commune
    public void deleteCommune(Long id) {
        communeRepository.deleteById(id);
    }



    // Méthodes CRUD pour Quartier
    public List<Quartier> getAllQuartiers() {
        return quartierRepository.findAll();
    }

    public Optional<Quartier> getQuartierById(Long id) {
        return quartierRepository.findById(id);
    }

    public Quartier createQuartier(Quartier quartier) {
        return quartierRepository.save(quartier);
    }

        public Double calculateDensity(DonneeGenerale data) {
            // Vérifiez d'abord si toutes les données nécessaires sont présentes
            if (data.getSurfaceUrbanisee() != null && data.getSurfaceUrbanisee() != 0) {
                // Effectuez le calcul de densité
                return (double) data.getNombreLogements() / data.getSurfaceUrbanisee();
            } else {
                // Gérez le cas où la surface urbanisée est nulle ou égale à zéro
                return 0.0; // Ou lancez une exception ou gérez de manière appropriée selon votre cas d'utilisation
            }
        }
    // Méthode pour mettre à jour un quartier existant
    public Quartier updateQuartier(Long id, Quartier quartierDetails) {
        Optional<Quartier> optionalQuartier = quartierRepository.findById(id);
        if (optionalQuartier.isPresent()) {
            Quartier quartier = optionalQuartier.get();
            quartier.setName(quartierDetails.getName());
            // Mettez à jour d'autres propriétés si nécessaire
            return quartierRepository.save(quartier);
        } else {
            // Gérer l'erreur si le quartier n'est pas trouvé
            return null;
        }
    }

    // Méthode pour supprimer un quartier
    public void deleteQuartier(Long id) {
        quartierRepository.deleteById(id);
    }

    // Méthode pour obtenir les quartiers d'une commune donnée
    public List<Delegation> getDelegationsByGouvernaurat(long gouvernauratId) {
        return delegationRepository.findByGouvernoratId(gouvernauratId);
    }

    public List<Commune> getCommunesByDelegation(long delecationId) {
        return communeRepository.findByDelegationId(delecationId);
    }

    public List<Quartier> getQuartiersByCommune(long communeId) {
        return quartierRepository.findByCommuneId(communeId);
    }


}





