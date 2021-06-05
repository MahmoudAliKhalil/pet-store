package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.entities.Product;
import eg.gov.iti.jets.petstore.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductResource {
    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts() {
        return new ArrayList<>(0);
    }
}
