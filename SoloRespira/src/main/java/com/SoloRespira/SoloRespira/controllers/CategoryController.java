package com.SoloRespira.SoloRespira.controllers;

import com.SoloRespira.SoloRespira.dtos.CategoryRequestDto;
import com.SoloRespira.SoloRespira.dtos.CategoryResponseDto;
import com.SoloRespira.SoloRespira.dtos.MessageDto;
import com.SoloRespira.SoloRespira.entities.Category;
import com.SoloRespira.SoloRespira.exceptions.GeneralException;
import com.SoloRespira.SoloRespira.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService _categoryService;


    public CategoryController(CategoryService categoryService) {
        this._categoryService = categoryService;
    }

    // Get all categories
    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        return new ResponseEntity<>(_categoryService.getAllCategories(), HttpStatus.OK);
    }


    // Get a category by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable(name = "id") String id){
        try {
            return  new ResponseEntity<>(_categoryService.getCategoryById(id), HttpStatus.OK);
        }catch (GeneralException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Create category
    @PostMapping
    public ResponseEntity<MessageDto> createCategory(@RequestBody @Valid CategoryRequestDto dto) {
        try {
            return new ResponseEntity<>(_categoryService.createCategory(dto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Update Category
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateCategory (@RequestBody @Valid CategoryRequestDto dto, @PathVariable String id){
        try{
            return new ResponseEntity<>(_categoryService.updateCategory(id, dto), HttpStatus.OK);
        }catch (GeneralException ex){
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete category, use soft delete
    @PutMapping("/delete/{id}")
    public ResponseEntity<MessageDto> deleteCategory(@PathVariable String id){
        try {
            return new ResponseEntity<>(_categoryService.deleteCategory(id),HttpStatus.OK);
        } catch (GeneralException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
