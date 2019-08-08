package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidadPK;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ActividadesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActividadServicioImpl implements ActividadServicio{

    private ActividadesRepositorio actividadesRepositorio;

    @Autowired
    public ActividadServicioImpl(ActividadesRepositorio actividadesRepositorio, EstudiantesRepositorio estudiantesRepositorio){
        this.actividadesRepositorio = actividadesRepositorio;
    }

    public void agregarActividad(ActividadEntidad actividadEntidad){

    }

    public void borrarActividad(ActividadEntidad actividadEntidad){

    }

    public void modificarActividad(ActividadEntidad actividadEntidad){

    }

    public Page<ActividadEntidad> getActividades(Integer pagina, Integer limite){
        return null;
    }

    public ArrayList<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante, Integer pagina, Integer limite){
        ArrayList<ActividadEntidad> actividadEntidadArrayList = actividadesRepositorio.findByActividadEntidadPKCorreoEstudiante(correoEstudiante);
        return actividadEntidadArrayList;
    }
}
