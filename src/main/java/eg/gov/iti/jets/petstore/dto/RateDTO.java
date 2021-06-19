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
    @Schema(description = "customer username", example = "Alex")
    private String userName;
    @Schema(description = "The rating number between 0 to 5", example = "5")
    private Integer rateNumber;
}
