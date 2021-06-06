package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.CategoryDto;
import eg.gov.iti.jets.petstore.entities.Category;
import eg.gov.iti.jets.petstore.exceptions.CategoryException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Long id) throws CategoryException;
    CategoryDto addNewCategory(CategoryDto categoryDto) ;
}
