package eg.gov.iti.jets.petstore.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
public class Admin extends User {
}
