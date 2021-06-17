package eg.gov.iti.jets.petstore;

import eg.gov.iti.jets.petstore.entities.CartItem;
import eg.gov.iti.jets.petstore.entities.Customer;
import eg.gov.iti.jets.petstore.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;

@SpringBootApplication
public class PetStoreApplication implements CommandLineRunner {

    public PetStoreApplication(CustomerRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }

    private final CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer();
        customer.setEmail("hema");
        customer.setPassword("123");
        customer.setShoppingCart(new HashSet<CartItem>());

        repository.save(customer);

    }
}
