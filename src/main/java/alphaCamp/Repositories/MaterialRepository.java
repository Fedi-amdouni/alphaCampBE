package alphaCamp.Repositories;

import alphaCamp.Models.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    Optional<Material> findById(Integer id);
    Optional<Material> findByQuantityLeft(Integer quantity);
}
