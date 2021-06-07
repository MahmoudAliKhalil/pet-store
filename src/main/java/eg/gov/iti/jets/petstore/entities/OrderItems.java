package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItems {
    @EmbeddedId
    private OrderItemsId id;
    private Integer quantity;
    @Column(name = "price_after_discount")
    private Float priceAfterDiscount;
    @ManyToOne
    @MapsId("orderId")
    private Order order;
    @ManyToOne
    @MapsId("productId")
    private Product product;
}
