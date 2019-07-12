package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudiantesRepositorio extends JpaRepository<EstudianteEntidad, String> {
}
