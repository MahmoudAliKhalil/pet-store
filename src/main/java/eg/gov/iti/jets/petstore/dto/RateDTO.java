package eg.gov.iti.jets.petstore.dto;

import eg.gov.iti.jets.petstore.entities.RateId;
import eg.gov.iti.jets.petstore.entities.User;
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

    private RateId id;
    private Integer rateNumber;
}
