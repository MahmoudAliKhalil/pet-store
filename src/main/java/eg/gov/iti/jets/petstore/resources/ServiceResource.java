package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.ServiceDTO;
import eg.gov.iti.jets.petstore.dto.ServiceDTO;
import eg.gov.iti.jets.petstore.exceptions.models.ErrorDetails;
import eg.gov.iti.jets.petstore.services.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "services", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceResource {
    public final ServiceService serviceService;

    public ServiceResource(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Operation(summary = "find all services.",
            description = "Retrieve all services.")
    @ApiResponse(responseCode = "204", description = "Empty list of services.", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successfully retrieve services.")
    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getServices(@Parameter(description = "Number of pages to retrieve.", example = "0") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                      @Parameter(description = "Number of accounts in the page.", example = "10") @RequestParam(name = "pageLimit", defaultValue = "10") Integer pageLimit) {
        List<ServiceDTO> services = serviceService.getAllServices(page, pageLimit);
        HttpStatus httpStatus = services.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(services);
    }

    @Operation(summary = "find specific service.",
            description = "Retrieve specific Service.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieve service.")
    @ApiResponse(responseCode = "404", description = "Service not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceDTO getService(@Parameter(description = "Service unique identifier.", example = "123", required = true) @PathVariable("id") Long id) {
        return serviceService.getService(id);
    }

//    @Operation(summary = "find specific service Service.",
//            description = "Retrieve Service related to specific service account.")
//    @ApiResponse(responseCode = "200", description = "Successfully retrieve service Service.")
//    @ApiResponse(responseCode = "404", description = "Service account not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
//    @GetMapping("{id}/")
//    @ResponseStatus(HttpStatus.OK)
//    public ServiceDTO getServiceProducts(@Parameter(description = "Service account unique identifier.", example = "123", required = true) @PathVariable("id") Long id) {
//        return serviceService.getProviderService(id);
//    }

    @Operation(summary = "Add new service .",
            description = "Insert new service.")
    @ApiResponse(responseCode = "201", description = "Successfully created service .")
    @ApiResponse(responseCode = "400", description = "Illegal representation of the service.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceDTO addService(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Service information.", required = true) @RequestBody ServiceDTO service) {
        return serviceService.addService(service);
    }

    @Operation(summary = "Delete all service accounts.",
            description = "Delete all accounts with service privilege.")
    @ApiResponse(responseCode = "200", description = "Successfully delete service accounts.")
    @ApiResponse(responseCode = "404", description = "Service accounts not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteServices() {
        serviceService.deleteAllServices();
    }

    @Operation(summary = "Update specific service.",
            description = "Update specific service.")
    @ApiResponse(responseCode = "200", description = "Successfully update service.")
    @ApiResponse(responseCode = "404", description = "Service not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ServiceDTO updateService(@Parameter(description = "Service unique identifier.", example = "123", required = true) @PathVariable("id") Long id,
                                  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Service information.", required = true) @RequestBody ServiceDTO service) {
        return serviceService.updateService(id, service);
    }

    @Operation(summary = "Delete specific service.",
            description = "Delete specific service.")
    @ApiResponse(responseCode = "200", description = "Successfully delete service.")
    @ApiResponse(responseCode = "404", description = "Service not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteServices(@Parameter(description = "Service unique identifier.", example = "123", required = true) @PathVariable("id") Long id) {
        serviceService.deleteService(id);
    }
}
