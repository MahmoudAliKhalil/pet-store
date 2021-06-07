package eg.gov.iti.jets.petstore.resources;

import eg.gov.iti.jets.petstore.dto.CategoryDTO;
import eg.gov.iti.jets.petstore.services.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{categoryId}")
    @ApiOperation(value = "Finds Category by id",
    notes = "Provide and id to look up specific category")
    public ResponseEntity<CategoryDTO> getCategoryById(@ApiParam(value = "Id value for the category you need to retrieve ", required = true) @PathVariable Long categoryId){
        CategoryDTO category = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(category);

    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> allCategory = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(allCategory);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> addNewCategory(@RequestBody CategoryDTO categoryDto){
        CategoryDTO newCategory = categoryService.addNewCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }
}
