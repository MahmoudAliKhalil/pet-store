package eg.gov.iti.jets.petstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class BrandDTO {
    @Schema(description = "Brand unique identifier", example = "1")
    private Integer id;
    @Schema(description = "Brand name", example = "Persian")
    private String name;
}
