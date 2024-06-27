package pfe.jwt_spring.identification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pfe.jwt_spring.identification.Entities.CouvertureReseaux;
import pfe.jwt_spring.identification.Entities.TypeReseau;
import pfe.jwt_spring.identification.repository.CouvertureReseauxRepository;
import pfe.jwt_spring.identification.repository.TypeReseauRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CouvertureReseauxService {

    private final CouvertureReseauxRepository couvertureReseauxRepository;
    private final TypeReseauRepository typeReseauRepository;

    @Autowired
    public CouvertureReseauxService(CouvertureReseauxRepository couvertureReseauxRepository, TypeReseauRepository typeReseauRepository) {
        this.couvertureReseauxRepository = couvertureReseauxRepository;
        this.typeReseauRepository = typeReseauRepository;
    }


    public List<CouvertureReseaux> getAllCouvertureReseaux() {
        return couvertureReseauxRepository.findAll();
    }

    public List<CouvertureReseaux> saveAll(List<CouvertureReseaux> couvertureReseauxList) {
        return couvertureReseauxRepository.saveAll(couvertureReseauxList);
    }

    public Optional<CouvertureReseaux> getCouvertureReseauxById(Long id) {
        return couvertureReseauxRepository.findById(id);
    }

    public void deleteCouvertureReseauxById(Long id) {
        couvertureReseauxRepository.deleteById(id);
    }
}