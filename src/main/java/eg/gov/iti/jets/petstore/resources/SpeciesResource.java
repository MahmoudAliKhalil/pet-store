package eg.gov.iti.jets.petstore.resources;


import eg.gov.iti.jets.petstore.dto.SpeciesDTO;
import eg.gov.iti.jets.petstore.services.SpeciesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{speciesName}")
    @ApiOperation(value = "Find species by name",
            notes = "Provide a name to look up specific species")
    public ResponseEntity<SpeciesDTO> getSpeciesByName(@ApiParam(value = "Name of the species you need to retrieve ", required = true) @PathVariable String speciesName){
        SpeciesDTO species = speciesService.getSpeciesByName(speciesName);
        return ResponseEntity.status(HttpStatus.OK).body(species);

    }

    @GetMapping
    public ResponseEntity<List<SpeciesDTO>> getAllSpecies(){
        List<SpeciesDTO> allSpecies = speciesService.getAllSpecies();
        return ResponseEntity.status(HttpStatus.OK).body(allSpecies);
    }

    @PostMapping
    public ResponseEntity<SpeciesDTO> addNewSpecies(@RequestBody SpeciesDTO speciesDTO){
        SpeciesDTO newSpecies = speciesService.addNewSpecies(speciesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newSpecies);
    }
}
