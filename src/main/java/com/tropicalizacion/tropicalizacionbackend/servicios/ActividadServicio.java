package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import org.springframework.data.domain.Page;

import java.util.ArrayList;


public interface ActividadServicio {

    ActividadEntidad agregarActividad(ActividadEntidad actividadEntidad);

    void borrarActividad(ActividadEntidad actividadEntidad);

    void modificarActividad(ActividadEntidad actividadEntidad);

    Page<ActividadEntidad> getActividades(Integer pagina, Integer limite);

    ArrayList<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante);

    ActividadEntidad consultarActividadPorId(Integer id);
}
