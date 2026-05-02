package alphaCamp.Services;

import alphaCamp.Models.Material;
import alphaCamp.Repositories.MaterialRepository;
import alphaCamp.Requests.MaterialRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MaterialServiceImpl  implements MaterialService {

    private final MaterialRepository materialRepository;

//Public methods
    public Material insertItem(MaterialRequest materialRequest) {
        Material material = requestToMaterial(materialRequest);
        materialRepository.save(material);
        return  material;
    }

    public Optional<Material> getItemById(Integer materialId) {
        if (materialId != null)
            return materialRepository.findById(materialId);
        return Optional.empty();
    }

    public Material updateItem(MaterialRequest materialRequest, Integer materialId) {
        Material material = materialRepository.findById(materialId).orElseThrow(()->
                new IllegalArgumentException("Material with id :" + materialId + " is not found"));
        if (materialRequest.getDescription() != null)
            material.setDescription(materialRequest.getDescription());
        if (materialRequest.getNom() != null)
            material.setNom(materialRequest.getNom());
        if (materialRequest.getPlace() != null)
            material.setPlace(materialRequest.getPlace());
        if (materialRequest.getModel() != null)
            material.setModel(materialRequest.getModel());
        if (materialRequest.getQuantityLeft() != null)
            material.setQuantityLeft(materialRequest.getQuantityLeft());
        if (materialRequest.getUserId() != null)
            material.setUserId(materialRequest.getUserId());
        materialRepository.save(material);
        return material;
    }

    public boolean deleteItem(Integer materialId) {
        if (materialRepository.existsById(materialId)) {
            materialRepository.deleteById(materialId);
            return true;
        }
        return false;
    }

    public List<Material> getAllItems(){
        return materialRepository.findAll();
    }

//Private methods
    private Material requestToMaterial(MaterialRequest materialRequest) {
        return Material.builder()
                .userId(materialRequest.getUserId())
                .nom(materialRequest.getNom())
                .model(materialRequest.getModel())
                .place(materialRequest.getPlace())
                .description(materialRequest.getDescription())
                .quantityLeft(materialRequest.getQuantityLeft())
                .build();
    }

}
