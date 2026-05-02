package alphaCamp.Controllers;

import alphaCamp.Models.Material;
import alphaCamp.Requests.MaterialRequest;
import alphaCamp.Services.MaterialServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialServiceImpl materialService;

    @PostMapping
    public ResponseEntity<Material> addItem(@RequestBody MaterialRequest materialRequest) {
        return ResponseEntity.ok(materialService.insertItem(materialRequest));
    }

    @GetMapping
    public ResponseEntity<List<Material>> getAllItems() {
        return ResponseEntity.ok(materialService.getAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Material>> getItem(@PathVariable Integer id) {
        return ResponseEntity.ok(materialService.getItemById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Material> updateItem(@RequestBody MaterialRequest materialRequest,@PathVariable Integer id) {
        return ResponseEntity.ok(materialService.updateItem(materialRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Material> deleteItem(@PathVariable Integer id) {
        materialService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}
