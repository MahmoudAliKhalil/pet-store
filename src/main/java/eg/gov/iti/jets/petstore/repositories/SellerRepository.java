package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
