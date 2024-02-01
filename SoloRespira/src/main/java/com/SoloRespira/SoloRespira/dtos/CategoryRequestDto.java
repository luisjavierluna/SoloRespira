package com.SoloRespira.SoloRespira.dtos;

import com.SoloRespira.SoloRespira.entities.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDto {


    @NotBlank(message = "Name field must not be empty")
    private String name;

    @NotBlank(message = "ImageId field must not be empty")
    private String imageId;

    @NotBlank(message = "DeparmentId field must not be empty")
    private String deparmentId;

    @NotBlank(message = "Products field must not be empty")
    private List<ProductRequestDto> products;

}
