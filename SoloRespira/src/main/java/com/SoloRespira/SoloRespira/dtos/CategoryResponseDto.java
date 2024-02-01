package com.SoloRespira.SoloRespira.dtos;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CategoryResponseDto {


    private String name;

    private String imageId;

    public CategoryResponseDto(String name, String imageId) {
        this.name = name;
        this.imageId = imageId;
    }
}
