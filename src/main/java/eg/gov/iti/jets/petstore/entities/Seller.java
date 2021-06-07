package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("SELLER")
@Getter
@Setter
public class Seller extends User {
    @OneToMany(mappedBy = "seller")
    private Set<Product> products;
}
