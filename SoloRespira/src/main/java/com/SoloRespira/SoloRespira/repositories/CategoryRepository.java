package com.SoloRespira.SoloRespira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.SoloRespira.SoloRespira.entities.*;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category save(Category category);

    //Buscar categoría por nombre
    Optional<Category> findByName(String name);

    // Eliminar categoría por Id
    void deleteById(String id);

    // Listar las categorías
    List<Category> findAll();



}
