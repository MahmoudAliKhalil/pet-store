package eg.gov.iti.jets.petstore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Details about the Category")
public class CategoryDTO {

    @Schema(description = "The unique id of the category")
    private int id;
    @Schema(description = "The category name")
    private String name;
}
