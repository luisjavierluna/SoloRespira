package com.SoloRespira.SoloRespira.entities;

import com.SoloRespira.SoloRespira.entities.*;
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
@Builder
@EqualsAndHashCode(callSuper = true)
@Table()
public class Product extends BaseEntity implements Serializable {

    @OneToOne
    private Image image;
    private Double price;
    private Double discount;
    private Double finalPrice;
    private String description;
    private Double weight;
    private Double height;
    private Double width;
    private Double length;
    private Integer initialPrice;
    private Integer soldUnits;
    private Integer currentStock;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Deparment deparment;
}

