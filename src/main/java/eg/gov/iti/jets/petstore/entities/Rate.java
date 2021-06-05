package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Rate {
    @EmbeddedId
    private RateId id;
    @ManyToOne(optional = false)
    @MapsId("userId")
    @ToString.Exclude
    private User user;
    @ManyToOne(optional = false)
    @MapsId("productId")
    @ToString.Exclude
    private Product product;
    @Column(name = "rate_number", nullable = false, length = 5)
    private Integer rateNumber;
}
