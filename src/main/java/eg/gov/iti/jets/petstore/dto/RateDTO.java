package eg.gov.iti.jets.petstore.dto;

import eg.gov.iti.jets.petstore.entities.RateId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the Rate")
public class RateDTO {

    @Schema(description = "Rate Id")
    private RateId id;
    @Schema(description = "Rate Number")
    private Integer rateNumber;
}
