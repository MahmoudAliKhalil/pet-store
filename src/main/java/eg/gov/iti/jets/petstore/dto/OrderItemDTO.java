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
@Schema(description = "Details about the Order Items")
public class OrderItemDTO {

    @Schema(description = "Order Items for any Order", example = "Cairo")
    private OrderItemsId id;
    @Schema(description = "quantity of item in system")
    private Integer quantity;
    @Schema(description = "item price")
    private Float priceAfterDiscount;

}
