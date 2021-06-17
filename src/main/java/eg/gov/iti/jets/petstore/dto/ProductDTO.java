package eg.gov.iti.jets.petstore.dto;

import eg.gov.iti.jets.petstore.entities.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the Product")
public class ProductDTO {

    @Schema(description = "Product Id")
    private Long id;
    @Schema(description = "Product Name")
    private String name;
    @Schema(description = "Product Description")
    private String description;
    @Schema(description = "Product Price")
    private Float price;
    private Integer quantity;
    @Schema(description = "Product category")
    private CategoryDTO category;
    @Schema(description = "Product Images")
    private Set<ProductImageDTO> images = new HashSet<>();
    //    private Boolean available ;
    @Schema(description = "Product Brand")
    private Brand brand;
    @Schema(description = "Product species")
    private Species species;
    @Schema(description = "Product discount")
    private Float discount;
    @Schema(description = "Product Rates")
    private Set<Rate> rates = new HashSet<>();
    @Schema(description = "Items in Order")
    private Set<OrderItems> orderItems;
}
