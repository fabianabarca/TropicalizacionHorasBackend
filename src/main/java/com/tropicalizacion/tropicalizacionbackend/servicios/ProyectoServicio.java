package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import org.springframework.data.domain.Page;

public interface ProyectoServicio {

    void agregarProyecto(ProyectoEntidad proyectoEntidad);

    void borrarProyecto(ProyectoEntidad proyectoEntidad);

    void modificarProyecto(ProyectoEntidad proyectoEntidad);

    Page<ProyectoEntidad> getProyectos(Integer pagina, Integer limite);

    ProyectoEntidad consultarProyectoPorId(String id);
}
