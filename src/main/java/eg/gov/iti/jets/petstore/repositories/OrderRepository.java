package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE o.user.id = ?1 and o.status = eg.gov.iti.jets.petstore.enums.OrderStatus.NOT_COMPLETED")
    Order getOrderByUserIdAndOrderStatus(Long userId);
}
