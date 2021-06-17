package eg.gov.iti.jets.petstore.services.impl;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.dto.ProductsDTO;
import eg.gov.iti.jets.petstore.entities.Product;
import eg.gov.iti.jets.petstore.exceptions.ResourceNotFoundException;
import eg.gov.iti.jets.petstore.repositories.ProductRepository;
import eg.gov.iti.jets.petstore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductsDTO getAllProducts(Integer page, Integer pageLimit) {
        Page<Product> products = productRepository.findAll(Pageable.ofSize(pageLimit).withPage(page));
        return ProductsDTO.builder()
                .numberOfPages(products.getTotalPages())
                .products(products.stream()
                        .map(e -> modelMapper.map(e, ProductDTO.class))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ProductDTO getProduct(Long id) {
        return productRepository.findById(id)
                .map(e -> modelMapper.map(e, ProductDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " not found."));
    }

    @Override
    public ProductDTO addProduct(ProductDTO product) {
        return modelMapper.map(productRepository.save(modelMapper.map(product, Product.class)), ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) {
        product.setId(id);
        return modelMapper.map(productRepository.save(modelMapper.map(product, Product.class)), ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundException("Product with id: " + id + " not found.");
        }
    }

    @Override
    public void deleteAllProducts() {
        productRepository.deleteAllInBatch();
    }

    @Override
    public ProductsDTO getProductsByCategoryId(Long id, Integer page, Integer pageLimit) {
        Page<Product> products = productRepository.findProductsByCategory_Id(id, Pageable.ofSize(pageLimit).withPage(page));
        return ProductsDTO.builder()
                .numberOfPages(products.getTotalPages())
                .products(
                        products.get()
                                .map(e -> modelMapper.map(e, ProductDTO.class))
                                .collect(Collectors.toList())
                ).build();

    }
}
