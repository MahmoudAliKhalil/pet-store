package eg.gov.iti.jets.petstore.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    @ManyToOne
    private Category category;
    @OneToMany(mappedBy = "product")
    private Set<ProductImage> images;
    @ManyToOne
    private User seller;
    private Boolean available = false;
    @ManyToOne
    private Brand brand;
    @ManyToOne
    private Species species;
    private Float discount;
    @OneToMany(mappedBy = "product")
    private Set<Rate> rates;
    @OneToMany(mappedBy = "product")
    private Set<OrderItems> orderItems;
}
