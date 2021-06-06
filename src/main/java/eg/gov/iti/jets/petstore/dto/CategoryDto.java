package eg.gov.iti.jets.petstore.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details about the Category")
public class CategoryDto {

    @ApiModelProperty(notes = "The unique id of the category")
    private int id;
    @ApiModelProperty(notes = "The category name")
    private String name;
}
