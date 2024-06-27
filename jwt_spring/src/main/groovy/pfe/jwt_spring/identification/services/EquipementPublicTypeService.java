package pfe.jwt_spring.identification.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.jwt_spring.identification.Entities.Equipement_Publics_type;
import pfe.jwt_spring.identification.repository.EquipementPublicTypeRepository;

import java.util.List;

@Service
public class EquipementPublicTypeService {

    @Autowired
    private EquipementPublicTypeRepository equipementPublicTypeRepository;

    public double calculateTotalCost(Long equipementPublicTypeId) {
        Equipement_Publics_type equipementPublicType = equipementPublicTypeRepository.findById(equipementPublicTypeId).orElseThrow(() -> new RuntimeException("EquipementPublicType not found"));
        return equipementPublicType.getHabitats().stream().mapToDouble(h -> h.getCout() * h.getQuantites()).sum();
    }

    public Equipement_Publics_type saveEquipementPublicType(Equipement_Publics_type equipementPublicType) {
        return equipementPublicTypeRepository.save(equipementPublicType);
    }

    public Equipement_Publics_type updateEquipementPublicType(Long id, Equipement_Publics_type updatedEquipementPublicType) {
        Equipement_Publics_type existingEquipementPublicType = equipementPublicTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EquipementPublicType not found with ID: " + id));

        existingEquipementPublicType.setType(updatedEquipementPublicType.getType());
        existingEquipementPublicType.setHabitats(updatedEquipementPublicType.getHabitats());

        return equipementPublicTypeRepository.save(existingEquipementPublicType);
    }

    public void deleteEquipementPublicType(Long id) {
        equipementPublicTypeRepository.deleteById(id);
    }

    public Equipement_Publics_type getEquipementPublicTypeById(Long id) {
        return equipementPublicTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EquipementPublicType not found with ID: " + id));
    }

    public List<Equipement_Publics_type> getAllEquipementPublicTypes() {
        return equipementPublicTypeRepository.findAll();
    }
}