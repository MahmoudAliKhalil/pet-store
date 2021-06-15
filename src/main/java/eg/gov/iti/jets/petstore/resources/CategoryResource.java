package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.CategoryDTO;
import eg.gov.iti.jets.petstore.dto.OrderDTO;
import eg.gov.iti.jets.petstore.dto.ProductDTO;
import eg.gov.iti.jets.petstore.exceptions.models.ErrorDetails;
import eg.gov.iti.jets.petstore.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryService categoryService;

    @Autowired
    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "/{categoryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Finds Category by id",
            description = "Provide and id to look up specific category"
    )
    public ResponseEntity<CategoryDTO> getCategoryById(@Parameter(in = ParameterIn.PATH, description = "Id value for the category you need to retrieve ", required = true) @PathVariable Long categoryId) {
        CategoryDTO category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(category);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> allCategory = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(allCategory);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDTO> addNewCategory(@RequestBody CategoryDTO categoryDto) {
        CategoryDTO newCategory = categoryService.addNewCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @Operation(summary = "find specific category products.",
            description = "Retrieve products related to specific category.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieve Category products.")
    @ApiResponse(responseCode = "204", description = "Empty list of Category products.", content = @Content)
    @ApiResponse(responseCode = "404", description = "Category not found.", content = @Content(schema = @Schema(implementation = ErrorDetails.class)))
    @GetMapping("{id}/products")
    public ResponseEntity<List<ProductDTO>> getCategoryProducts(@Parameter(description = "Category unique identifier.", example = "123", required = true) @PathVariable("id") Long id) {
        List<ProductDTO> products = categoryService.getCategoryProducts(id);
        HttpStatus status = products.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK;
        return ResponseEntity.status(status).body(products);
    }
}
