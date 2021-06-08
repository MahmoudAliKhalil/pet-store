package eg.gov.iti.jets.petstore.dto;

import eg.gov.iti.jets.petstore.entities.OrderItemsId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Details about the Category Items")
public class OrderItemDTO {

    private OrderItemsId id;
    private Integer quantity;
    private Float priceAfterDiscount;

}
