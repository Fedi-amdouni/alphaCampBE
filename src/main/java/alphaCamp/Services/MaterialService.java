package alphaCamp.Services;

import alphaCamp.Models.Material;
import alphaCamp.Requests.MaterialRequest;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    Material insertItem(MaterialRequest materialRequest);
    Optional<Material> getItemById(Integer materialId);
    List<Material> getAllItems();
    boolean deleteItem(Integer materialId);
    Material updateItem(MaterialRequest materialRequest, Integer materialId);
}
