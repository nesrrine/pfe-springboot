package pfe.jwt_spring.identification.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.Logement;
import pfe.jwt_spring.identification.repository.LogementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LogementService {

    @Autowired
    private LogementRepository logementRepository;


    public List<Logement> getAllLogements() {
        return logementRepository.findAll();
    }

    public Logement saveLogement(Logement logement) {
        return logementRepository.save(logement);
    }

    public Logement getLogementById(Long id) {
        Optional<Logement> logementOptional = logementRepository.findById(id);
        return logementOptional.orElse(null);
    }


    public void deleteLogement(Long id) {
        logementRepository.deleteById(id);
    }

    public Logement updateLogement(Long id, Logement updatedLogement) {
        Optional<Logement> logementOptional = logementRepository.findById(id);
        if (logementOptional.isPresent()) {
            Logement existingLogement = logementOptional.get();
            existingLogement.setType(updatedLogement.getType());
            existingLogement.setPourcentage(updatedLogement.getPourcentage());
            existingLogement.setQualite(updatedLogement.getQualite());
            return logementRepository.save(existingLogement);
        } else {
            return null;
        }
    }
}

