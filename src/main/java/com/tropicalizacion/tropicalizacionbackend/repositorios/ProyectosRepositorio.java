package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectosRepositorio extends JpaRepository<ProyectoEntidad, String> {

}
