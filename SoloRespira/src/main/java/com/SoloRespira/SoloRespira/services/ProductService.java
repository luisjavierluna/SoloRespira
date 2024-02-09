package com.SoloRespira.SoloRespira.services;

import com.SoloRespira.SoloRespira.Entities.BaseEntity;
import com.SoloRespira.SoloRespira.Entities.Image;
import com.SoloRespira.SoloRespira.Entities.Product;
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
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    ProductRepository _productRepository;
    ProductMapper _mapper;
    ImageService _imageService;
    GeneralApiService _apiService;
   
    @Autowired
    public ProductService(
            ProductRepository productRepository,
            ProductMapper mapper,
            ImageService imageService,
            GeneralApiService apiService) {
        
        _productRepository = productRepository;
        _mapper = mapper;
        _imageService = imageService;
        _apiService = apiService;
    }

    //READ
    public List<ProductResponseDto> getProducts(){
        
        List<ProductResponseDto> productsDTO = _productRepository.findAll()
                .stream().map(product -> _mapper.toResponseDTO(product))
                .collect(Collectors.toList());
        
        return productsDTO;
    }
    
    // READ
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
    public MessageDto createProduct(ProductRequestDto dto) throws GeneralException{        
        Product product = _mapper.toProduct(dto);
                
        product = (Product) _apiService.insertTrackingData((BaseEntity) product);
                
        // Attributos pendientes, se necesitan los CRUD's de Departments, Images y Categories
//        product.setDeparment(department);
//        product.setCategory(category);

        Image image = _imageService.save(dto.getImage());

        product.setImage(image);
        
        _productRepository.save(product);
        return new MessageDto("Producto "+product.getName()+" creado");
    }
    
    //UPDATE
    @Transactional
    public MessageDto updateProduct(ProductRequestDto dto, String id) throws GeneralException{
        Optional<Product> optionalProduct = _productRepository.findById(id);
        
        Product product;
        
        if (optionalProduct.isPresent()){
            product = optionalProduct.get();
            
            product = (Product) _apiService.updateTrackingData((BaseEntity) product);
            
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
//            product.setCategory(category);
            
            _imageService.update(dto.getImage(), product.getImage().getId());
            
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
            
            product = (Product) _apiService.updateTrackingData((BaseEntity) product);
            
            product.setIsDeleted(true);
            
            _imageService.delete(product.getImage().getId());
            
            _productRepository.save(product);
        }else{
            throw new GeneralException("No se tienen registros con el id: " + id);
        }

        return new MessageDto("Producto "+optionalProduct.get().getName()+" eliminado");
    }
}
