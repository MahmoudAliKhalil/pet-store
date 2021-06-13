package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Long> {

}
