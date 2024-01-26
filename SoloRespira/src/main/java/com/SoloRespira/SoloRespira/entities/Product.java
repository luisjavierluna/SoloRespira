package com.SoloRespira.SoloRespira.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @Builder // https://github.com/mapstruct/mapstruct/issues/2115
@Table()
public class Product extends BaseEntity implements Serializable {

    private Double price;
    private Double discount;
    private String description;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private Integer initialStock;
    private Integer soldUnits;
    
    @OneToOne
    private Image image;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Deparment deparment;
}

