package eg.gov.iti.jets.petstore.resources;


import eg.gov.iti.jets.petstore.dto.SpeciesDTO;
import eg.gov.iti.jets.petstore.services.SpeciesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
public class SpeciesResource {
    private final SpeciesService speciesService;

    @Autowired
    public SpeciesResource(SpeciesService speciesService) {
        this.speciesService = speciesService;
    }

    @GetMapping(path = "/{speciesName}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Find species by name",
            description = "Provide a name to look up specific species")
    public ResponseEntity<SpeciesDTO> getSpeciesByName(@Parameter(in = ParameterIn.PATH, description = "Name of the species you need to retrieve ", required = true) @PathVariable String speciesName) {
        SpeciesDTO species = speciesService.getSpeciesByName(speciesName);
        return ResponseEntity.status(HttpStatus.OK).body(species);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SpeciesDTO>> getAllSpecies() {
        List<SpeciesDTO> allSpecies = speciesService.getAllSpecies();
        return ResponseEntity.status(HttpStatus.OK).body(allSpecies);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SpeciesDTO> addNewSpecies(@RequestBody SpeciesDTO speciesDTO) {
        SpeciesDTO newSpecies = speciesService.addNewSpecies(speciesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSpecies);
    }
}
