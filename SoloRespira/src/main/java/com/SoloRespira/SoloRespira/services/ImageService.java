package com.SoloRespira.SoloRespira.services;

import com.SoloRespira.SoloRespira.Entities.BaseEntity;
import com.SoloRespira.SoloRespira.Entities.Image;
import com.SoloRespira.SoloRespira.Entities.Product;
import com.SoloRespira.SoloRespira.exceptions.GeneralException;
import com.SoloRespira.SoloRespira.repositories.ImageRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private ImageRepository _imageRepository;
    private GeneralApiService _apiService;

    public ImageService(
            ImageRepository imageRepository,
            GeneralApiService apiService) {
        _imageRepository = imageRepository;
        _apiService = apiService;
    }

    public Image save(MultipartFile file) throws GeneralException {
        if (file != null) {
            try {
                Image image = new Image();

                image = (Image) _apiService.insertTrackingData((BaseEntity) image);

                image.setMime(file.getContentType());

                image.setName(file.getName());

                image.setContent(file.getBytes());

                return _imageRepository.save(image);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return null;
    }

    public Image update(MultipartFile file, String idImage) throws GeneralException {
        if (file != null) {
            try {
                Image image = new Image();

                if (idImage != null) {
                    Optional<Image> respuesta = _imageRepository.findById(idImage);

                    if (respuesta.isPresent()) {
                        image = respuesta.get();
                    }
                }

                image = (Image) _apiService.updateTrackingData((BaseEntity) image);

                image.setMime(file.getContentType());
                image.setName(file.getName());
                image.setContent(file.getBytes());

                return _imageRepository.save(image);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }

        return null;
    }
    
    public Image delete(String idImage) throws GeneralException {
        try {
            Image image = new Image();

            if (idImage != null) {
                Optional<Image> respuesta = _imageRepository.findById(idImage);

                if (respuesta.isPresent()) {
                    image = respuesta.get();
                }
            }

            image = (Image) _apiService.updateTrackingData((BaseEntity) image);
            image.setIsDeleted(true);
            
            return null;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
