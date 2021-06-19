package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.ProductDTO;

import eg.gov.iti.jets.petstore.entities.Product;

import eg.gov.iti.jets.petstore.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductResource {

    public final ProductService productService;


    public ProductResource(ProductService productService) {
        this.productService = productService;
    }


    @Operation(summary = "find all product.",
            description = "Retrieve all product.")
    @ApiResponse(responseCode = "204", description = "Empty list of product", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successfully retrieve all product.")
    @GetMapping
    public ResponseEntity<ProductsDTO> getProducts(@Parameter(description = "Minimum price of products.", example = "0") @RequestParam(name = "price.lt", defaultValue = "0") Float minPrice,
                                                   @Parameter(description = "Maximum price of products.", example = "10000") @RequestParam(name = "price.gt", defaultValue = "" + Float.MAX_VALUE) Float maxPrice,
                                                   @Parameter(description = "Number of pages to retrieve.", example = "0") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @Parameter(description = "Number of accounts in the page.", example = "10") @RequestParam(name = "pageLimit", defaultValue = "10") Integer pageLimit) {
        ProductsDTO products = productService.getAllProducts(minPrice, maxPrice, page, pageLimit);
        HttpStatus httpStatus = products.getProducts().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(products);
    }

    @Operation(summary = "find specific product.",
            description = "Retrieve specific product.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieve produc.")
    @ApiResponse(responseCode = "404", description = "product not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProduct(@Parameter(description = "product unique identifier.", example = "123", required = true) @PathVariable("id") Long id) {
        return productService.getProduct(id);
    }

    @Operation(summary = "Add new product.",
            description = "Insert new product.")
    @ApiResponse(responseCode = "201", description = "Successfully created product.")
    @ApiResponse(responseCode = "400", description = "Illegal representation of the product.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Product information.", required = true) @RequestBody ProductDTO product) {
        return productService.addProduct(product);
    }

    @Operation(summary = "Delete all product.",
            description = "Delete all product.")
    @ApiResponse(responseCode = "200", description = "Successfully delete product.")
    @ApiResponse(responseCode = "404", description = "product not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteProducts() {
        productService.deleteAllProducts();
    }

    @Operation(summary = "Update specific product.",
            description = "Update specific product.")
    @ApiResponse(responseCode = "200", description = "Successfully update product.")
    @ApiResponse(responseCode = "404", description = "product not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@Parameter(description = "product unique identifier.", example = "123", required = true) @PathVariable("id") Long id,
                                    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "product information.", required = true) @RequestBody ProductDTO product) {
        return productService.updateProduct(id, product);
    }

    @Operation(summary = "Delete specific product.",
            description = "Delete specific product.")
    @ApiResponse(responseCode = "200", description = "Successfully delete product.")
    @ApiResponse(responseCode = "404", description = "product not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@Parameter(description = "product unique identifier.", example = "123", required = true) @PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @Operation(summary = "find category's products.",
            description = "Retrieve all products belonging to specific category.")
    @ApiResponse(responseCode = "204", description = "Empty list of product", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successfully retrieve all product.")
    @GetMapping(params = {"categoryId"})
    public ResponseEntity<ProductsDTO> getCategoryProducts(@Parameter(description = "Category unique identifier.", example = "1") @RequestParam(name = "categoryId") Long categoryId,
                                                           @Parameter(description = "Minimum price of products.", example = "0") @RequestParam(name = "price.lt", defaultValue = "0") Float minPrice,
                                                           @Parameter(description = "Maximum price of products.", example = "10000") @RequestParam(name = "price.gt", defaultValue = "" + Float.MAX_VALUE) Float maxPrice,
                                                           @Parameter(description = "Number of pages to retrieve.", example = "0") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                           @Parameter(description = "Number of accounts in the page.", example = "10") @RequestParam(name = "pageLimit", defaultValue = "10") Integer pageLimit) {
        ProductsDTO products = productService.getProductsByCategoryId(categoryId, minPrice, maxPrice, page, pageLimit);
        HttpStatus httpStatus = products.getProducts().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(products);
    }

    @Operation(summary = "find brand's products.",
            description = "Retrieve all products belonging to specific brand.")
    @ApiResponse(responseCode = "204", description = "Empty list of product", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successfully retrieve all product.")
    @GetMapping(params = {"brandId"})
    public ResponseEntity<ProductsDTO> getBrandProducts(@Parameter(description = "Brand unique identifier.", example = "1") @RequestParam(name = "brandId") Integer brandId,
                                                        @Parameter(description = "Minimum price of products.", example = "0") @RequestParam(name = "price.lt", defaultValue = "0") Float minPrice,
                                                        @Parameter(description = "Maximum price of products.", example = "10000") @RequestParam(name = "price.gt", defaultValue = "" + Float.MAX_VALUE) Float maxPrice,
                                                        @Parameter(description = "Number of pages to retrieve.", example = "0") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                        @Parameter(description = "Number of accounts in the page.", example = "10") @RequestParam(name = "pageLimit", defaultValue = "10") Integer pageLimit) {
        ProductsDTO products = productService.getProductsByBrandId(brandId, minPrice, maxPrice, page, pageLimit);
        HttpStatus httpStatus = products.getProducts().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(products);

    }

    @Operation(summary = "find products by category and brand.",
            description = "Retrieve all products belonging to specific category and brand.")
    @ApiResponse(responseCode = "204", description = "Empty list of product", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successfully retrieve all product.")
    @GetMapping(params = {"categoryId", "brandId"})
    public ResponseEntity<ProductsDTO> getProductsByCategoryAndBrand(@Parameter(description = "Category unique identifier.", example = "1") @RequestParam(name = "categoryId") Long categoryId,
                                                                     @Parameter(description = "brand unique identifier.", example = "1") @RequestParam(name = "brandId") Integer brandId,
                                                                     @Parameter(description = "Minimum price of products.", example = "0") @RequestParam(name = "price.lt", defaultValue = "0") Float minPrice,
                                                                     @Parameter(description = "Maximum price of products.", example = "10000") @RequestParam(name = "price.gt", defaultValue = "" + Float.MAX_VALUE) Float maxPrice,
                                                                     @Parameter(description = "Number of pages to retrieve.", example = "0") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                     @Parameter(description = "Number of accounts in the page.", example = "10") @RequestParam(name = "pageLimit", defaultValue = "10") Integer pageLimit) {
        ProductsDTO products = productService.getProductsByCategoryAndBrand(categoryId, brandId, minPrice, maxPrice, page, pageLimit);
        HttpStatus httpStatus = products.getProducts().isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(httpStatus).body(products);
    }

}
