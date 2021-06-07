package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGE")
@Getter
@Setter
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @ManyToOne
    private Product product;
}
