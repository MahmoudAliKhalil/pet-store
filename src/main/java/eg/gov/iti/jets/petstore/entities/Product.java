package eg.gov.iti.jets.petstore.entities;

import eg.gov.iti.jets.petstore.enums.Species;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Integer quantity;
    @ManyToOne
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private Set<ProductImage> images;
    @ManyToOne
    @ToString.Exclude
    private User seller;
    private Boolean available = false;
    @ManyToOne
    @ToString.Exclude
    private Brand brand;
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "tinyint")
    private Species specie;
    @ManyToOne
    @JoinColumn(name = "specie", insertable = false, updatable = false)
    private Specie species;
    private Float discount;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private Set<Rate> rates;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private Set<OrderItems> orderItems;
}
