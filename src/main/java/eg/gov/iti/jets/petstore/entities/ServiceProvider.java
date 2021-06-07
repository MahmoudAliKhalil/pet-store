package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("SERVICE_PROVIDER")
@Getter
@Setter
public class ServiceProvider extends User {
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Service> service;
}
