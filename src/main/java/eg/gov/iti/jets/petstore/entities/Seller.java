package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("SELLER")
@Data
public class Seller extends User {
    @OneToMany(mappedBy = "seller")
    @ToString.Exclude
    private Set<Product> products;
}
