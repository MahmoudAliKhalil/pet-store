package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("BUYER")
@Data
public class Buyer extends User {
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Order> orders;
}
