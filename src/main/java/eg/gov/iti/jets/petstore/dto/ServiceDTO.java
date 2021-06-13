package eg.gov.iti.jets.petstore.dto;

import eg.gov.iti.jets.petstore.entities.ServiceProvider;
import eg.gov.iti.jets.petstore.entities.ServiceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.Duration;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the Service")
public class ServiceDTO {
    @Schema(description = "id for service")
    private Long id;
    @Schema(description = "Service name")
    private String name;
    @Schema(description = "Service price")
    private Float price;
    @Schema(description = "Service description")
    private String description;
    @Schema(description = "Service current discount")
    private Float discount;
    @Schema(description = "Starting time within work days to provide service")
    private LocalTime startTime;
    @Schema(description = "End time within work days to provide service")
    private LocalTime endTime;
    @Schema(description = "Required time to provide service")
    private Duration timePerService;
    @Schema(description = "If Service is provided from home or not")
    private Boolean deliverable;
    @Schema(description = "Data of the service provider")
    private ServiceProviderDTO provider;
    @Schema(description = "Type of the service")
    private ServiceType type;

}
