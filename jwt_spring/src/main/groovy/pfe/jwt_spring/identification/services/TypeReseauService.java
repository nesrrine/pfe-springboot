package pfe.jwt_spring.identification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.TypeReseau;
import pfe.jwt_spring.identification.repository.TypeReseauRepository;

import java.util.List;
import java.util.Optional;
@Service
public class TypeReseauService {
    @Autowired
    private TypeReseauRepository typeReseauRepository;

    public List<TypeReseau> getAllTypeReseaux() {
        return typeReseauRepository.findAll();
    }

    public Optional<TypeReseau> getTypeReseauById(Long id) {
        return typeReseauRepository.findById(id);
    }

    public TypeReseau saveOrUpdateTypeReseau(TypeReseau typeReseau) {
        return typeReseauRepository.save(typeReseau);
    }

    public void deleteTypeReseauById(Long id) {
        typeReseauRepository.deleteById(id);
    }
}


