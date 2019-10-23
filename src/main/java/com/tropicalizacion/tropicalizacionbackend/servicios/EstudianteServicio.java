package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import org.springframework.data.domain.Page;

public interface EstudianteServicio {

    void agregarEstudiante(EstudianteEntidad estudianteEntidad);

    void borrarEstudiante(EstudianteEntidad estudianteEntidad);

    Page<EstudianteEntidad> getEstudiantes(Integer pagina, Integer limite);

    EstudianteEntidad consultarEstudiantePorId(String id);

    void editarProyectos(EstudianteEntidad estudianteEntidad, String[] proyectos);

    void editarEstudiante(EstudianteEntidad estudianteEntidad, EstudianteEntidad nuevoEstudiante);
}
