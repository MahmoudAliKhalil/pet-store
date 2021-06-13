package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.dto.OrderDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts(Integer page, Integer pageLimit);

    ProductDTO getProduct(Long id);

    ProductDTO addProduct(ProductDTO product);

    ProductDTO updateProduct(Long id, ProductDTO product);

    void deleteProduct(Long id);

    void deleteAllProducts();

}
