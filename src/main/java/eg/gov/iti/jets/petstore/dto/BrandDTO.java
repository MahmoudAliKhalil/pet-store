package eg.gov.iti.jets.petstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Brand details about the Product")
public class BrandDTO {
    @Schema(description = "Brand id")
    private Integer id;
    @Schema(description = "Brand name")
    private String name;
}
