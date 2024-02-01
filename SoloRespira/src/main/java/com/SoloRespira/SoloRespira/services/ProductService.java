package com.SoloRespira.SoloRespira.services;

import com.SoloRespira.SoloRespira.entities.Product;
import com.SoloRespira.SoloRespira.dtos.MessageDto;
import com.SoloRespira.SoloRespira.dtos.ProductRequestDto;
import com.SoloRespira.SoloRespira.dtos.ProductResponseDto;
import com.SoloRespira.SoloRespira.exceptions.GeneralException;
import com.SoloRespira.SoloRespira.mappings.ProductMapper;
import com.SoloRespira.SoloRespira.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Service
public class ProductService {

    ProductRepository _productRepository;
    ProductMapper _mapper;
   
    public ProductService(
            ProductRepository productRepository,
            ProductMapper mapper) {
        
        _productRepository = productRepository;
        _mapper = mapper;
    }

    //READ
    public List<ProductResponseDto> getProducts(){
        
        List<ProductResponseDto> productsDTO = _productRepository.findAll()
                .stream().map(product -> _mapper.toResponseDTO(product))
                .collect(Collectors.toList());
        
        return productsDTO;
    }
    
    public ProductResponseDto getProductById(String id) throws GeneralException{
        Optional<Product> optionalProduct = _productRepository.findById(id);
        
        if (optionalProduct.isPresent()) {
             ProductResponseDto dto = optionalProduct
                     .map(product -> _mapper.toResponseDTO(product))
                    .orElse(null);
             
             return dto;
        } else {
            throw new GeneralException("No se tienen registros con el id: " + id);
        }   
    }
    
    //CREATE
    @Transactional
    public MessageDto createProduct(ProductRequestDto dto){        
        Product product = _mapper.toProduct(dto);
        
        // Attributos incompletos
        product.setCreatedBy("Admin 1"); // Se necesita configurar la seguridad web
        product.setCreated(LocalDate.now());
        product.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
        product.setLastModified(LocalDate.now());
        product.setIsDeleted(false);
        
        // Attributos pendientes, se necesitan los CRUD's de Departments, Images y Categories
//        product.setDeparment(department);
//        product.setImage(category);
//        product.setCategory(category);
        
        _productRepository.save(product);
        return new MessageDto("Producto "+product.getDescription()+" creado");
    }
    
    //UPDATE
    @Transactional
    public MessageDto updateProduct(ProductRequestDto dto, String id) throws GeneralException{
        Optional<Product> optionalProduct = _productRepository.findById(id);
        
        Product product;
        
        if (optionalProduct.isPresent()){
            product = optionalProduct.get();

            product.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
            product.setLastModified(LocalDate.now());
            
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setDiscount(dto.getDiscount());
            product.setDescription(dto.getDescription());
            product.setWeight(dto.getWeight());
            product.setHeight(dto.getHeight());
            product.setWidth(dto.getWidth());
            product.setLength(dto.getLength());
            product.setInitialStock(dto.getInitialStock());
            product.setSoldUnits(dto.getSoldUnits());
            
            // Attributos pendientes, se necesitan los CRUD's de Departments, Images y Categories
//            product.setDeparment(department);
//            product.setImage(category);
//            product.setCategory(category);
            
            _productRepository.save(product);
            
        }else{
            throw new GeneralException("No se tienen registros con el id: " + id);
        }
        return new MessageDto(dto.getName()+" actualizado");
    }

    //DELETE
    @Transactional
    public MessageDto deleteProduct(String id) throws GeneralException{
        Optional<Product> optionalProduct = _productRepository.findById(id);
        
        Product product;

        if (optionalProduct.isPresent()){
            product = optionalProduct.get();
            
            product.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
            product.setLastModified(LocalDate.now());
            product.setIsDeleted(true);
            
            _productRepository.save(product);
        }else{
            throw new GeneralException("No se tienen registros con el id: " + id);
        }

        return new MessageDto("Producto "+optionalProduct.get().getName()+" eliminado");
    }
}
