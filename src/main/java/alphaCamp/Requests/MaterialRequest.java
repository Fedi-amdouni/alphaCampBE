package alphaCamp.Requests;

import alphaCamp.Enum.MaterialModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialRequest {
    private Integer userId;
    private String nom;
    private MaterialModel model;
    private String description;
    private Double unitPrice;
    private Integer quantityLeft;
    private String place;
}
