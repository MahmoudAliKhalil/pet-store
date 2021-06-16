package eg.gov.iti.jets.petstore.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Setter
@Getter

public class CartItem {
    @EmbeddedId
    private CartItemId cartItemId;
    private Integer quantity;

    @OneToOne
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        if (this.quantity > 0)
            this.quantity--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return product.equals(cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
