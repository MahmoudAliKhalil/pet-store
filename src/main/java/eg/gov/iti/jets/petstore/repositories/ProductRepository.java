package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
