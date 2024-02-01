package com.SoloRespira.SoloRespira.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String createdBy;
    private LocalDate created;
    private String lastModifiedBy;

    private LocalDate lastModified;

    private Boolean isDeleted;

}
