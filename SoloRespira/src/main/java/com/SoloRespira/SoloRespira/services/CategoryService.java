package com.SoloRespira.SoloRespira.services;


import com.SoloRespira.SoloRespira.dtos.CategoryRequestDto;
import com.SoloRespira.SoloRespira.dtos.CategoryResponseDto;
import com.SoloRespira.SoloRespira.dtos.MessageDto;
import com.SoloRespira.SoloRespira.dtos.ProductResponseDto;
import com.SoloRespira.SoloRespira.entities.Category;
import com.SoloRespira.SoloRespira.exceptions.GeneralException;
import com.SoloRespira.SoloRespira.mappings.CategoryMapper;
import com.SoloRespira.SoloRespira.mappings.ProductMapper;
import com.SoloRespira.SoloRespira.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository _categoryRepository;

    private final CategoryMapper _mapperCategory;

    private final ProductMapper _mapperProduct;


    public CategoryService(CategoryRepository _categoryRepository, CategoryMapper _mapperCategory, ProductMapper _mapperProduct) {
        this._categoryRepository = _categoryRepository;
        this._mapperCategory = _mapperCategory;
        this._mapperProduct = _mapperProduct;
    }

    public List<CategoryResponseDto> getAllCategories() {

        List<CategoryResponseDto> categories = _categoryRepository.findAll()
                .stream().map(category -> _mapperCategory.toResponseDTO(category))
                .collect(Collectors.toList());
        return categories;

    }

    public CategoryResponseDto getCategoryById(String id) throws GeneralException {

        Optional<Category> optionalCategory = _categoryRepository.findById(id);

        if (optionalCategory.isPresent()){
            CategoryResponseDto dto = optionalCategory
                    .map(_mapperCategory::toResponseDTO)
                    .orElse(null);

            return dto;
        } else {
            throw new GeneralException("No se tienen registros con el id: " + id);
        }
    }


    @Transactional
    public MessageDto createCategory(CategoryRequestDto categoryDto) {

        Category category = _mapperCategory.toCategory(categoryDto);
        category.setCreated(LocalDate.now());
        category.setCreatedBy(category.getCreatedBy());
        category.setLastModifiedBy(category.getLastModifiedBy()); // Se necesita configurar la seguridad web
        category.setLastModified(LocalDate.now());
        category.setIsDeleted(false);

        // Revisar el tema de los permisos, haciendo una comparación con el Servicio de Product, no me queda claro de
        // como se harán las validaciones de si la persona que está creando una categoría tiene los permisos o el rol
        // necesario para crear una category o un product


        _categoryRepository.save(category);
        return new MessageDto("Categoría "+category.getName()+" creado");

    }

    @Transactional
    public MessageDto updateCategory(String id, CategoryRequestDto categoryRequestDto) throws GeneralException {

        Optional<Category> optionalCategory = _categoryRepository.findById(id);

        Category category;

        if (optionalCategory.isPresent()){
            category = optionalCategory.get();

            category.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
            category.setLastModified(LocalDate.now());
            category.setImage(_categoryRepository.findByName(categoryRequestDto.getName()).get().getImage());
            category.setDeparment(_categoryRepository.findByName(categoryRequestDto.getName()).get().getDeparment());
            category.setProducts(categoryRequestDto.getProducts()
                    .stream().map(_mapperProduct::toProduct)
                    .collect(Collectors.toList()));

            _categoryRepository.save(category);
        }else{
            throw new GeneralException("No se tienen registros con el id: " + id);
        }
        return new MessageDto(categoryRequestDto.getName()+" actualizado");
    }

    @Transactional
    public MessageDto deleteCategory(String id) throws GeneralException{
    Optional<Category> optionalCategory = _categoryRepository.findById(id);

    Category category;
        if (optionalCategory.isPresent()){

            category= optionalCategory.get();

            category.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
            category.setLastModified(LocalDate.now());
            category.setIsDeleted(true);

            _categoryRepository.save(category);
        }else{
            throw new GeneralException("No se tienen registros con el id: " + id);
        }

        return new MessageDto("Categoria "+ optionalCategory.get().getName() + " eliminado");



    }
}
