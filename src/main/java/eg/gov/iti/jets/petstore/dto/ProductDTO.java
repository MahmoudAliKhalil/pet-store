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

    private Long id;
    private String name;
    private String description;
    private Float price;
//    private Integer quantity;
    private CategoryDTO category;
    private Set<ProductImageDTO> images = new HashSet<>();
//    private Boolean available ;
    private Brand brand;
    private Species species;
    private Float discount;
    private Set<Rate> rates= new HashSet<>();
    private Set<OrderItems> orderItems;
}
