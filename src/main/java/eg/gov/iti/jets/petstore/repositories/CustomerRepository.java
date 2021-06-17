package eg.gov.iti.jets.petstore.repositories;


import eg.gov.iti.jets.petstore.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
