package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstudiantesRepositorio extends JpaRepository<EstudianteEntidad, String> {
    @Query("SELECT e FROM EstudianteEntidad e JOIN e.proyectos p WHERE p.nombre = :nombreProyecto")
    Page<EstudianteEntidad> estudiantesDeProyecto(String nombreProyecto, Pageable pageable);

    @Query("SELECT DISTINCT e FROM EstudianteEntidad e LEFT JOIN e.proyectos p WHERE e.correoUsuario NOT IN (SELECT e2.correoUsuario FROM EstudianteEntidad e2 JOIN e2.proyectos p2 WHERE p2.nombre = :nombreProyecto)")
    Page<EstudianteEntidad> estudiantesNoEnProyecto(String nombreProyecto, Pageable pageable);
}
