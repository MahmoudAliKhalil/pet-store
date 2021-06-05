package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
