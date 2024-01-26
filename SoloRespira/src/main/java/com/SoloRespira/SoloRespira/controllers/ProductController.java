package com.SoloRespira.SoloRespira.controllers;

import com.SoloRespira.SoloRespira.dtos.MessageDto;
import com.SoloRespira.SoloRespira.dtos.ProductRequestDto;
import com.SoloRespira.SoloRespira.dtos.ProductResponseDto;
import com.SoloRespira.SoloRespira.exceptions.GeneralException;
import com.SoloRespira.SoloRespira.services.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    ProductService _productService;

    public ProductController(ProductService productService) {
        _productService = productService;
    }

    //Get all products
    @GetMapping("")
    public ResponseEntity<List<ProductResponseDto>> getProducts(){
        return new ResponseEntity<>(_productService.getProducts(), HttpStatus.OK);
    }
    
    // Get one product by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id){
        try {
            return new ResponseEntity<>(_productService.getProductById(id),HttpStatus.OK);
        } catch (GeneralException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // Create one product
    @PostMapping
    public ResponseEntity<MessageDto> createProduct(@RequestBody @Valid ProductRequestDto dto){
        try {
            return new ResponseEntity<>(_productService.createProduct(dto),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageDto(e.getMessage()), HttpStatus.BAD_REQUEST);   
        }
    }

    // Update one product
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateProduct(@RequestBody @Valid ProductRequestDto dto,@PathVariable String id){
        try {
            return new ResponseEntity<>(_productService.updateProduct(dto,id),HttpStatus.OK);
        } catch (GeneralException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete one product, use soft delete
    @PutMapping("/delete/{id}")
    public ResponseEntity<MessageDto> deleteProduct(@PathVariable String id){
        try {
            return new ResponseEntity<>(_productService.deleteProduct(id),HttpStatus.OK);
        } catch (GeneralException ex) {
            return new ResponseEntity<>(new MessageDto(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
