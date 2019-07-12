package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.CategoriaEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriasRepositorio extends JpaRepository<CategoriaEntidad, String> {
}
