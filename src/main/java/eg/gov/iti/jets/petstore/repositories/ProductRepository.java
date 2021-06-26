package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Product p set p.quantity =:newQuantity where p.id =:id")
    void updateProductQuantity(@Param("newQuantity") Integer newQuantity, @Param("id") Long id);



    Page<Product> findAllByPriceBetween(Float minPrice, Float maxPrice, Pageable pageable);

    Page<Product> findProductsByCategory_IdAndPriceBetween(Long id, Float minPrice, Float maxPrice, Pageable pageable);

    Page<Product> findProductsByBrand_IdAndPriceBetween(Integer id, Float minPrice, Float maxPrice, Pageable pageable);

    Page<Product> findProductsByCategory_IdAndBrand_IdAndPriceBetween(Long categoryId, Integer brandId, Float minPrice, Float maxPrice, Pageable pageable);

}
