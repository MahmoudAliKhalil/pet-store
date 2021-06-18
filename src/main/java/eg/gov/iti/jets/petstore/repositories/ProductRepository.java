package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByPriceBetween(Float minPrice, Float maxPrice, Pageable pageable);

    Page<Product> findProductsByCategory_IdAndPriceBetween(Long id, Float minPrice, Float maxPrice, Pageable pageable);

    Page<Product> findProductsByBrand_IdAndPriceBetween(Integer id, Float minPrice, Float maxPrice, Pageable pageable);

    Page<Product> findProductsByCategory_IdAndBrand_IdAndPriceBetween(Long categoryId, Integer brandId, Float minPrice, Float maxPrice, Pageable pageable);
}
