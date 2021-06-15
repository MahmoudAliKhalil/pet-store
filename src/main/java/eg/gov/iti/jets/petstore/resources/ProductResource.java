package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.exceptions.models.ErrorDetails;
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

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<List<ProductDTO>> getProducts(@Parameter(description = "Number of pages to retrieve.", example = "0") @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                          @Parameter(description = "Number of accounts in the page.", example = "10") @RequestParam(name = "pageLimit", defaultValue = "10") Integer pageLimit) {
        List<ProductDTO> products = productService.getAllProducts(page, pageLimit);
        HttpStatus httpStatus = products.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
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
}
