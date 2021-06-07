package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.CategoryDTO;
import eg.gov.iti.jets.petstore.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
}
