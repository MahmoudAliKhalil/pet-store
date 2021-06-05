package eg.gov.iti.jets.petstore.repositories;

import eg.gov.iti.jets.petstore.entities.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecieRepository extends JpaRepository<Specie, Integer> {
}
