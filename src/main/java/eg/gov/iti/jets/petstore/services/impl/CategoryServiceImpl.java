package eg.gov.iti.jets.petstore.services.Impl;

import eg.gov.iti.jets.petstore.dto.CategoryDTO;
import eg.gov.iti.jets.petstore.entities.Category;
import eg.gov.iti.jets.petstore.exceptions.CategoryException;
import eg.gov.iti.jets.petstore.repositories.CategoryRepository;
import eg.gov.iti.jets.petstore.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = categoryList
                .stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());

        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(Long id) throws CategoryException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
           return categoryDTO;
        }else{
            throw new CategoryException("Category with id " + id + " not found");
        }
    }

    @Override
    public CategoryDTO addNewCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categoryAfterSaved = categoryRepository.save(category);
        CategoryDTO newCategoryDTO = modelMapper.map(categoryAfterSaved, CategoryDTO.class);
        return newCategoryDTO;
    }
}
