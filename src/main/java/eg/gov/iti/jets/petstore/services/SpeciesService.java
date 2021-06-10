package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.SpeciesDTO;

import java.util.List;

public interface SpeciesService {
    List<SpeciesDTO> getAllSpecies();

    SpeciesDTO getSpeciesByName(String speciesName);

    SpeciesDTO addNewSpecies(SpeciesDTO speciesDTO);

    Boolean deleteSpecies(String speciesName);
}
