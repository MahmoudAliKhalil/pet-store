package eg.gov.iti.jets.petstore.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("ROLE_SERVICE_PROVIDER")
@Getter
@Setter
public class ServiceProvider extends User {
    @OneToOne(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Service service;
}
