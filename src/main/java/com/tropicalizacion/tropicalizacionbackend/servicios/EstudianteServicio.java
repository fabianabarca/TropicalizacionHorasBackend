package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface EstudianteServicio {

    void agregarEstudiante(EstudianteEntidad estudianteEntidad);

    void borrarEstudiante(EstudianteEntidad estudianteEntidad);

    void modificarEstudiante(EstudianteEntidad estudianteEntidad);

    Page<EstudianteEntidad> getEstudiantes(Integer pagina, Integer limite);

    EstudianteEntidad consultarEstudiantePorId(String id);
}
