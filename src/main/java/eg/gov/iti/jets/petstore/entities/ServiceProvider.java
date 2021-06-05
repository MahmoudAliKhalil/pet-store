package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("SERVICE_PROVIDER")
@Data
public class ServiceProvider extends User {
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Service> service;
}
