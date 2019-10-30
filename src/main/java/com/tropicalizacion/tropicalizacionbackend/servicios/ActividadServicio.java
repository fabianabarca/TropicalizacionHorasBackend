package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;

import java.util.ArrayList;
import java.util.List;


public interface ActividadServicio {

    ActividadEntidad agregarActividad(ActividadEntidad actividadEntidad);

    void borrarActividad(Integer id);

    void modificarActividad(Integer id, ActividadEntidad actividadEntidad, String userEmail);

    public List<ActividadEntidad> getActividades(boolean aceptadas, boolean pendientes, boolean rechazadas);

    ArrayList<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante);

    ActividadEntidad consultarActividadPorId(Integer id);
}
