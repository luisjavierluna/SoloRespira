package com.SoloRespira.SoloRespira.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDto {
    private String id;
    private String name;
    private Double price;
    private Double discount;
    private Double finalPrice;
    private String description;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private Integer initialStock;
    private Integer soldUnits;
    private Integer currentStock;
    private String departmentId;
    private String departmentName;
    private String categoryId;
    private String categoryName;
    
    public ProductResponseDto(String id, String name, Double price, Double discount, Double finalPrice, String description, Double weight, Double height, Double width, Double length, Integer initialStock, Integer soldUnits, Integer currentStock, String departmentId, String departmentName, String categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.finalPrice = this.price * this.discount;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.length = length;
        this.initialStock = initialStock;
        this.soldUnits = soldUnits;
        this.currentStock = this.initialStock - this.soldUnits;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}


