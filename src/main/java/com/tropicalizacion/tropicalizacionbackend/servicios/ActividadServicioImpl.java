package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ActividadesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ActividadServicioImpl implements ActividadServicio{

    private ActividadesRepositorio actividadesRepositorio;
    private EstudiantesRepositorio estudiantesRepositorio;

    @Autowired
    public ActividadServicioImpl(ActividadesRepositorio actividadesRepositorio, EstudiantesRepositorio estudiantesRepositorio){
        this.actividadesRepositorio = actividadesRepositorio;
        this.estudiantesRepositorio = estudiantesRepositorio;
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

    public Page<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante, Integer pagina, Integer limite){
        EstudianteEntidad estudianteEntidad = estudiantesRepositorio.findById(correoEstudiante).orElse(null);
        Page<ActividadEntidad> actividadEntidadPage = actividadesRepositorio.findByEstudiante(estudianteEntidad, PageRequest.of(pagina, limite));
        return actividadEntidadPage;
    }
}
