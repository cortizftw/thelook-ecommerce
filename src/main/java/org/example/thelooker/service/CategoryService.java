package org.example.thelooker.service;

import org.example.thelooker.dto.CategoryDto;
import org.example.thelooker.model.Category;

import java.util.List;

public interface CategoryService {
    void saveCategory(CategoryDto categoryDto);
    List<Category> findAllCategories();
    void updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
