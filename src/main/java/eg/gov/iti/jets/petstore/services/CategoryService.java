package eg.gov.iti.jets.petstore.services;

import eg.gov.iti.jets.petstore.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategory();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO addNewCategory(CategoryDTO categoryDto);
}
