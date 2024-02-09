package com.SoloRespira.SoloRespira.services;

import com.SoloRespira.SoloRespira.Entities.BaseEntity;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class GeneralApiService {
    
    
    public BaseEntity insertTrackingData(BaseEntity entity) {
     
        entity.setCreatedBy("Admin 1"); // Se necesita configurar la seguridad web
        entity.setCreated(LocalDate.now());
        entity.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
        entity.setLastModified(LocalDate.now());
        entity.setIsDeleted(false);
        
        return entity;
    }
    
    public BaseEntity updateTrackingData(BaseEntity entity) {
     
        entity.setLastModifiedBy("Admin 1"); // Se necesita configurar la seguridad web
        entity.setLastModified(LocalDate.now());
        
        return entity;
    }
}
