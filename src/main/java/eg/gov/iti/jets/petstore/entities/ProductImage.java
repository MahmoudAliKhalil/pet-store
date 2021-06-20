package eg.gov.iti.jets.petstore.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_IMAGE")
@Data
@Builder
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @ManyToOne
    private Product product;
}
