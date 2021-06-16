package eg.gov.iti.jets.petstore.repositories;


import eg.gov.iti.jets.petstore.entities.Customer;
import eg.gov.iti.jets.petstore.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //TODO Check if works
    Set<CartItem> findShoppingCartById(Long id);

}
