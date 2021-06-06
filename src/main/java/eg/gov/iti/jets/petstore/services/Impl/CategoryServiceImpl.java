package eg.gov.iti.jets.petstore.services.Impl;

import eg.gov.iti.jets.petstore.dto.CategoryDto;
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
    public List<CategoryDto> getAllCategory() {
        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categoryList
                .stream()
                .map(category -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());

        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategoryById(Long id) throws CategoryException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category category = categoryOptional.get();
            CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
           return categoryDto;
        }else{
            throw new CategoryException("Category with id " + id + " not found");
        }
    }

    @Override
    public CategoryDto addNewCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category categoryAfterSaved = categoryRepository.save(category);
        CategoryDto newCategoryDto = modelMapper.map(categoryAfterSaved, CategoryDto.class);
        return newCategoryDto;
    }
}
