package eg.gov.iti.jets.petstore.services.Impl;


import eg.gov.iti.jets.petstore.dto.SpeciesDTO;
import eg.gov.iti.jets.petstore.entities.Species;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
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
        return speciesList
                .stream()
                .map(species -> modelMapper.map(species, SpeciesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public SpeciesDTO getSpeciesByName(String speciesName) {
        Optional<Species> speciesOptional = speciesRepository.findSpeciesByName(speciesName);
        if (speciesOptional.isPresent()) {
            Species species = speciesOptional.get();
            return modelMapper.map(species, SpeciesDTO.class);
        } else {
            throw new ResourceNotFoundException("Species " + speciesName + " is not found");
        }

    }

    @Override
    public SpeciesDTO getSpeciesById(Integer speciesId){
        Optional<Species> speciesOptional = speciesRepository.findById(speciesId);
        if (speciesOptional.isPresent()) {
            Species species = speciesOptional.get();
            SpeciesDTO speciesDTO = modelMapper.map(species, SpeciesDTO.class);
            return speciesDTO;
        } else {
            throw new ResourceNotFoundException("Species no. " + speciesId + " is not found");
        }
    }

    @Override
    public SpeciesDTO addNewSpecies(SpeciesDTO speciesDTO) {
        Species species = modelMapper.map(speciesDTO, Species.class);
        Species speciesAfterSaved = speciesRepository.save(species);
        return modelMapper.map(speciesAfterSaved, SpeciesDTO.class);
    }

    @Override
    public SpeciesDTO updateSpecies(Integer id, SpeciesDTO speciesDTO) {
        speciesDTO.setSpeciesId(id);
        Species species = modelMapper.map(speciesDTO, Species.class);
        Species speciesAfterUpdate = speciesRepository.save(species);
        SpeciesDTO newSpeciesDTO = modelMapper.map(speciesAfterUpdate, SpeciesDTO.class);
        return newSpeciesDTO;
    }

    @Override
    public Boolean deleteSpecies(String speciesName) {
        long numberOfDeletedSpecies = speciesRepository.deleteSpeciesByName(speciesName);
        if (numberOfDeletedSpecies != 1) {
            throw new ResourceNotFoundException("Species " + speciesName + " is not found");
        }
        return true;
    }

    @Override
    public void deleteSpecies(Integer speciesId) {
        speciesRepository.deleteById(speciesId);
    }
}

