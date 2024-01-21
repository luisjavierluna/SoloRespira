package com.SoloRespira.SoloRespira.services;

import com.SoloRespira.SoloRespira.dto.CategoryDTO;
import com.SoloRespira.SoloRespira.entities.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(String id);

    Category getCategoryByName(String name);

    void createCategory(Category category);

    Category updateCategory(String id, Category category);

    void deleteCategory(String id);



}
