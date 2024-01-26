package com.SoloRespira.SoloRespira.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
// @Builder
@Table
public class Category extends BaseEntity implements Serializable {

    @OneToOne
    private Image image;

    @ManyToOne
    private Deparment deparment;

    @OneToMany
    private List<Product> products;
}
