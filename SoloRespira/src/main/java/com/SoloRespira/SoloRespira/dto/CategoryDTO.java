package com.SoloRespira.SoloRespira.dto;

import com.SoloRespira.SoloRespira.entities.Deparment;
import com.SoloRespira.SoloRespira.entities.Image;
import com.SoloRespira.SoloRespira.entities.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class CategoryDTO {

    private String name;

    private Image image;

    private DeparmentDTO deparment;

    private List<ProductDTO> products;





}
