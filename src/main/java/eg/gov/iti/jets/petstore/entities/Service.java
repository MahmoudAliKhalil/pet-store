package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalTime;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float price;
    private String description;
    private Float discount;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration timePerService;
    private Boolean deliverable;
    @ManyToOne(optional = false)
    @ToString.Exclude
    private User provider;
    @ManyToOne(optional = false)
    @ToString.Exclude
    private ServiceType type;
}
