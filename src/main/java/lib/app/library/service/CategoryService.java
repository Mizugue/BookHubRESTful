package lib.app.library.service;

import lib.app.library.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAllCategories();
    CategoryDTO getCategoryById(Long id);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    String deleteCategory(Long id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, Long id);

}
