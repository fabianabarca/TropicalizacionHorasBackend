package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface ActividadServicio {

    void agregarActividad(ActividadEntidad actividadEntidad);

    void borrarActividad(ActividadEntidad actividadEntidad);

    void modificarActividad(ActividadEntidad actividadEntidad);

    Page<ActividadEntidad> getActividades(Integer pagina, Integer limite);

    Page<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante, Integer pagina, Integer limite);
}
