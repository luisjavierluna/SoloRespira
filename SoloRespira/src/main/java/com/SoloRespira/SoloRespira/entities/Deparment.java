package com.SoloRespira.SoloRespira.Entities;

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
