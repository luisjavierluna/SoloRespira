package com.SoloRespira.SoloRespira.services.impl;

import com.SoloRespira.SoloRespira.dto.CategoryDTO;
import com.SoloRespira.SoloRespira.entities.Category;
import com.SoloRespira.SoloRespira.repositories.CategoryRepository;
import com.SoloRespira.SoloRespira.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;

    }

    @Override
    public Category getCategoryById(String id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        Category category = categoryOptional.get();
        return category;
    }

    @Override
    public Category getCategoryByName(String name) {
        Optional<Category> categoryOptional = categoryRepository.findByName(name);
        Category category = categoryOptional.get();
        return category;
    }

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(String id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).get();
        Category categoryUpdated = new Category();
        categoryUpdated.setProducts(categoryToUpdate.getProducts());
        categoryUpdated.setName(categoryToUpdate.getName());
        categoryUpdated.setDeparment(categoryToUpdate.getDeparment());
        categoryUpdated.setImage(categoryToUpdate.getImage());
        return categoryUpdated;
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
