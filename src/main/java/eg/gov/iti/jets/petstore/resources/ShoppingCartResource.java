package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.services.CustomerService;
import eg.gov.iti.jets.petstore.services.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{userId}/shopping-cart/")
public class ShoppingCartResource {

    private final ShoppingCartService service;

    public ShoppingCartResource(ShoppingCartService service) {
        this.service = service;
    }


    @Operation(summary = "add New Product To Shopping cart.",
            description = "Retrieve all product.")
    @ApiResponse(responseCode = "204", description = "Empty list of product", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successfully retrieve all product.")
    @PatchMapping
    public ResponseEntity<?> addToShoppingCart(@RequestBody Long productId,  @PathVariable  Long userId) {








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
}
