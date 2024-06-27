package pfe.jwt_spring.identification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.Infrastructurestype;
import pfe.jwt_spring.identification.repository.InfrastructureTypeRepository;

import java.util.List;

@Service
public class InfrastructureTypeService {

    @Autowired
    private InfrastructureTypeRepository infrastructureTypeRepository;


    public Infrastructurestype addType(Infrastructurestype type) {
        return infrastructureTypeRepository.save(type);
    }

    public List<Infrastructurestype> getAllTypes() {
        return infrastructureTypeRepository.findAll();
    }

    public Infrastructurestype updateInfrastructureType(Long id, Infrastructurestype updatedInfrastructureType) {
        Infrastructurestype existingInfrastructureType = infrastructureTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InfrastructureType not found with ID: " + id));

        existingInfrastructureType.setType(updatedInfrastructureType.getType());
        existingInfrastructureType.setInfrastructurestypeList(updatedInfrastructureType.getInfrastructurestypeList());

        return infrastructureTypeRepository.save(existingInfrastructureType);
    }

    public void deleteInfrastructureType(Long id) {
        infrastructureTypeRepository.deleteById(id);
    }

    public Infrastructurestype getInfrastructureTypeById(Long id) {
        return infrastructureTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InfrastructureType not found with ID: " + id));
    }

    public List<Infrastructurestype> getAllInfrastructureTypes() {
        return infrastructureTypeRepository.findAll();
    }
}
