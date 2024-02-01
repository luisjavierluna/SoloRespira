package com.SoloRespira.SoloRespira.mappings;

import com.SoloRespira.SoloRespira.dtos.CategoryRequestDto;
import com.SoloRespira.SoloRespira.dtos.CategoryResponseDto;
import com.SoloRespira.SoloRespira.dtos.ProductRequestDto;
import com.SoloRespira.SoloRespira.dtos.ProductResponseDto;
import com.SoloRespira.SoloRespira.entities.Category;
import com.SoloRespira.SoloRespira.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;



    @Mapper(
            componentModel = MappingConstants.ComponentModel.SPRING
    )

    public interface CategoryMapper {

        CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

        @Mappings( {

                @Mapping(source = "name", target = "name"),
                @Mapping(source = "image.id", target = "imageId")
        })
        CategoryResponseDto toResponseDTO(Category category);


        @Mapping(source = "name", target = "name")
        Category toCategory(CategoryRequestDto categoryRequestDto);

    }


