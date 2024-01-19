package com.SoloRespira.SoloRespira.Entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity implements Serializable {

    private String mime;
    @Lob @Basic(fetch = FetchType.LAZY)
    private Byte[] content;

}
