package com.SoloRespira.SoloRespira.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table
public class Category extends BaseEntity implements Serializable {


    private Image image;

    private Deparment deparment;

    @OneToMany
    private List<Product> products;
}
