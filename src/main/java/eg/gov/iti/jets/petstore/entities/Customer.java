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
@DiscriminatorValue("ROLE_CUSTOMER")
@Getter
@Setter
public class Customer extends User {
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
}
