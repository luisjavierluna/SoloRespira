package com.SoloRespira.SoloRespira.dtos;

import com.SoloRespira.SoloRespira.Entities.Image;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    
    @NotBlank(message = "Name field must not be empty")
    private String name;
    
    @NotNull(message = "Price must contain a value")
    @Positive(message = "Price must be positive")
    private Double price;

    @NotNull(message = "discount must contain a value")
    @Positive(message = "discount must be positive")
    private Double discount;

    @NotBlank(message = "Name field must not be empty")
    private String description;

    @NotNull(message = "weight must contain a value")
    @Positive(message = "weight must be positive")
    private Double weight;

    @NotNull(message = "height must contain a value")
    @Positive(message = "height must be positive")
    private Double height;

    @NotNull(message = "width must contain a value")
    @Positive(message = "width must be positive")
    private Double width;
    @NotNull(message = "length must contain a value")
    @Positive(message = "length must be positive")
    private Double length;

    @NotNull(message = "initialStock must contain a value")
    @Positive(message = "initialStock must be positive")
    private Integer initialStock;

    @NotNull(message = "soldUnits must contain a value")
    @Positive(message = "soldUnits must be positive")
    private Integer soldUnits;
    
    @NotBlank(message = "departmentId field must not be empty")
    private String departmentId; 

    @NotBlank(message = "categoryId field must not be empty")
    private String categoryId;
    
    private MultipartFile image;
    
    // private Image image;
}
