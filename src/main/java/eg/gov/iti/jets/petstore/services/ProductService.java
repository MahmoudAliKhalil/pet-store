package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.dto.ProductsDTO;

public interface ProductService {
    ProductsDTO getAllProducts(Float minPrice, Float maxPrice, Integer page, Integer pageLimit);

    ProductDTO getProduct(Long id);

    ProductDTO addProduct(ProductDTO product);

    ProductDTO updateProduct(Long id, ProductDTO product);

    void deleteProduct(Long id);

    void deleteAllProducts();

    ProductsDTO getProductsByCategoryId(Long id, Float minPrice, Float maxPrice, Integer page, Integer pageLimit);

    ProductsDTO getProductsByBrandId(Integer id, Float minPrice, Float maxPrice, Integer page, Integer pageLimit);

    ProductsDTO getProductsByCategoryAndBrand(Long categoryId, Integer brandId, Float minPrice, Float maxPrice, Integer page, Integer pageLimit);
}
