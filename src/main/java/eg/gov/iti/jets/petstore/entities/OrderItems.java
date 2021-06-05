package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class OrderItems {
    @EmbeddedId
    private OrderItemsId id;
    private Integer quantity;
    @Column(name = "price_after_discount")
    private Float priceAfterDiscount;
    @ManyToOne
    @MapsId("orderId")
    @ToString.Exclude
    private Order order;
    @ManyToOne
    @MapsId("productId")
    @ToString.Exclude
    private Product product;
}
