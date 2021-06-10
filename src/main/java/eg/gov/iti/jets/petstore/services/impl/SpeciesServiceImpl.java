package eg.gov.iti.jets.petstore.services.impl;


import eg.gov.iti.jets.petstore.dto.SpeciesDTO;
import eg.gov.iti.jets.petstore.entities.Species;
import eg.gov.iti.jets.petstore.exceptions.SpeciesException;
import eg.gov.iti.jets.petstore.repositories.SpeciesRepository;
import eg.gov.iti.jets.petstore.services.SpeciesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeciesServiceImpl implements SpeciesService {

    private final SpeciesRepository speciesRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SpeciesServiceImpl(SpeciesRepository speciesRepository, ModelMapper modelMapper) {
        this.speciesRepository = speciesRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public List<SpeciesDTO> getAllSpecies() {
        List<Species> speciesList = speciesRepository.findAll();
        List<SpeciesDTO> speciesDTOList = speciesList
                .stream()
                .map(species -> modelMapper.map(species, SpeciesDTO.class))
                .collect(Collectors.toList());

        return speciesDTOList;
    }

    @Override
    public SpeciesDTO getSpeciesByName(String speciesName) throws SpeciesException {
        Optional<Species> speciesOptional = speciesRepository.findSpeciesByName(speciesName);
        if (speciesOptional.isPresent()) {
            Species species = speciesOptional.get();
            SpeciesDTO speciesDTO = modelMapper.map(species, SpeciesDTO.class);
            return speciesDTO;
        } else {
            throw new SpeciesException("Species " + speciesName + " is not found");
        }

    }

    @Override
    public SpeciesDTO addNewSpecies(SpeciesDTO speciesDTO) {
        Species species = modelMapper.map(speciesDTO, Species.class);
        Species speciesAfterSaved = speciesRepository.save(species);
        SpeciesDTO newSpeciesDTO = modelMapper.map(speciesAfterSaved, SpeciesDTO.class);
        return newSpeciesDTO;
    }

    @Override
    public Boolean deleteSpecies(String speciesName) {
        long numberOfDeletedSpecies = speciesRepository.deleteSpeciesByName(speciesName);
        if(numberOfDeletedSpecies != 1){
            throw new SpeciesException("Species " + speciesName + " is not found");
        }
        return true;
    }
}

