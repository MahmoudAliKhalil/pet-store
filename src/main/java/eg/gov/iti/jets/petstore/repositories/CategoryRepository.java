package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
