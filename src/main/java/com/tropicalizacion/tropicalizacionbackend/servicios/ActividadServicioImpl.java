package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ActividadesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.CategoriasRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ProyectosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActividadServicioImpl implements ActividadServicio{

    private ActividadesRepositorio actividadesRepositorio;
    private EstudiantesRepositorio estudiantesRepositorio;
    private ProyectosRepositorio proyectosRepositorio;
    private CategoriasRepositorio categoriasRepositorio;

    @Autowired
    public ActividadServicioImpl(ActividadesRepositorio actividadesRepositorio,
                                 EstudiantesRepositorio estudiantesRepositorio,
                                 ProyectosRepositorio proyectosRepositorio,
                                 CategoriasRepositorio categoriasRepositorio
    ){
        this.actividadesRepositorio = actividadesRepositorio;
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.categoriasRepositorio = categoriasRepositorio;
        this.proyectosRepositorio = proyectosRepositorio;
    }

    public void agregarActividad(ActividadEntidad actividadEntidad){
        try{
            EstudianteEntidad estudianteEntidad = estudiantesRepositorio.findById(actividadEntidad.getEstudiante().getUsuario().getCorreo()).orElse(null);
            ProyectoEntidad proyectoEntidad = proyectosRepositorio.findById(actividadEntidad.getProyecto().getNombre()).orElse(null);
            CategoriaEntidad categoriaEntidad = categoriasRepositorio.findById(actividadEntidad.getCategoria().getNombre()).orElse(null);
            actividadEntidad.setEstudiante(estudianteEntidad);
            actividadEntidad.setProyecto(proyectoEntidad);
            actividadEntidad.setCategoria(categoriaEntidad);
            actividadesRepositorio.save(actividadEntidad);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void borrarActividad(ActividadEntidad actividadEntidad){

    }

    public void modificarActividad(ActividadEntidad actividadEntidad){

    }

    public Page<ActividadEntidad> getActividades(Integer pagina, Integer limite){
        return null;
    }

    public ArrayList<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante){
        ArrayList<ActividadEntidad> actividadEntidadArrayList = actividadesRepositorio.findByEstudiante(EstudianteEntidad.builder().correoUsuario(correoEstudiante).build());
        return actividadEntidadArrayList;
    }
}
