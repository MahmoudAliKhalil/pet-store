package eg.gov.iti.jets.petstore;

import eg.gov.iti.jets.petstore.entities.Category;
import eg.gov.iti.jets.petstore.entities.Product;
import eg.gov.iti.jets.petstore.entities.ProductImage;
import eg.gov.iti.jets.petstore.entities.Species;
import eg.gov.iti.jets.petstore.repositories.CustomerRepository;
import eg.gov.iti.jets.petstore.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;

@SpringBootApplication
public class PetStoreApplication implements CommandLineRunner {
    private final CustomerRepository repository;
    private final ProductRepository productRepository;

    public PetStoreApplication(CustomerRepository repository, ProductRepository productRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(PetStoreApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
//        Customer customer = new Customer();
////        customer.setId(1l);
//        customer.setFirstName("Ebrahim");
//        customer.setLastName("Mohamed");
//        customer.setEmail("ebrahim@gmail.com");
//        customer.setPassword("12345");
//        customer.setActive(true);
//        customer.setBirthDate(LocalDate.now());
//        customer.setNotLocked(true);
//        customer.setGender(Gender.MALE);
//        customer.setPhoneNumber("01024261189");
//        customer.setRole(Roles.ROLE_CUSTOMER);
//        Address address = new Address();
//        address.setCity("Fayoum");
//        address.setCountry("Egypt");
//        address.setStreet("El Shaikh");
//        customer.setAddress(address);
//        customer.setBirthDate(LocalDate.of(1996,01,26));
//        customer.setUserName("ebrahim-mohamed");
//        repository.save(customer);
//        for(int i=3; i<9; i++){
//            Product product = new Product();
//            product.setAvailable(true);
//            product.setDescription("Prodcut "+i);
//            product.setDiscount(12.3f);
//            product.setName("Prodcut "+i);
//            product.setPrice(110.3f+i);
//            product.setQuantity(14+i);
//            Category category = new Category();
//            category.setName("Category"+i);
//            category.setId((long) i);
//            product.setCategory(category);
//
//            Species species = new Species();
//            species.setId(i);
//            species.setName("species"+i);
//            product.setSpecies(species);
//
//            productRepository.save(product);
//        }


    }
}
