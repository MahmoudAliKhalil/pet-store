package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
