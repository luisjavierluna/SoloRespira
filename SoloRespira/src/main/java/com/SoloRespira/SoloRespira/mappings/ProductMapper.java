package com.SoloRespira.SoloRespira.mappings;

import com.SoloRespira.SoloRespira.entities.Product;
import com.SoloRespira.SoloRespira.dtos.ProductRequestDto;
import com.SoloRespira.SoloRespira.dtos.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
        )

public interface ProductMapper {
    
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class);
    
    @Mappings( {
        @Mapping(source = "deparment.id", target = "departmentId"),
        @Mapping(source = "deparment.name", target = "departmentName"),
        @Mapping(source = "category.id", target = "categoryId"),
        @Mapping(source = "category.name", target = "categoryName")
    })
    ProductResponseDto toResponseDTO(Product product);
    
    
    @Mapping(source = "name", target = "name")
    Product toProduct(ProductRequestDto product);
    
}
