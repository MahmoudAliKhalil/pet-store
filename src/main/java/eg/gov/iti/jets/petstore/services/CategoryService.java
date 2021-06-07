package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.CategoryDTO;
import eg.gov.iti.jets.petstore.exceptions.CategoryException;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategory();
    CategoryDTO getCategoryById(Long id) throws CategoryException;
    CategoryDTO addNewCategory(CategoryDTO categoryDto) ;
}
