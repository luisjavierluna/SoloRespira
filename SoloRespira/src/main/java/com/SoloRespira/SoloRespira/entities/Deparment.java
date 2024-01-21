package com.SoloRespira.SoloRespira.entities;

import com.SoloRespira.SoloRespira.entities.BaseEntity;
import com.SoloRespira.SoloRespira.entities.Category;
import com.SoloRespira.SoloRespira.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table
public class Deparment extends BaseEntity implements Serializable {

    private String description;

    @OneToMany
    private List<Category> categories;

    @OneToMany
    private List<Product> products;

}
