package eg.gov.iti.jets.petstore.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Specie {
    @Id
    @Column(columnDefinition = "tinyint")
    private Integer id;
    private String name;
}
