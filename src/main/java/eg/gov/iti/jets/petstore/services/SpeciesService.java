package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.SpeciesDTO;
import eg.gov.iti.jets.petstore.exceptions.SpeciesException;

import java.util.List;

public interface SpeciesService {
    List<SpeciesDTO> getAllSpecies();
    SpeciesDTO getSpeciesByName(String speciesName) throws SpeciesException;
    SpeciesDTO addNewSpecies(SpeciesDTO speciesDTO) ;
    Boolean deleteSpecies(String speciesName);
}
