package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import org.springframework.data.domain.Page;

public interface ProyectoServicio {

    void agregarProyecto(ProyectoEntidad proyectoEntidad);

    void borrarProyecto(String proyectoNombre);

    void modificarDescripcionProyecto(String nombre, String nuevaDescripcion);

    Page<ProyectoEntidad> getProyectos(Integer pagina, Integer limite);

    ProyectoEntidad consultarProyectoPorId(String id);

    Page<EstudianteEntidad> estudiantesProyecto(String nombre, Integer pagina, Integer limite);

    void removerEstudiante(String proyecto, String estudiante);

    void agregarParticipante(String proyecto, String estudiante);
}
