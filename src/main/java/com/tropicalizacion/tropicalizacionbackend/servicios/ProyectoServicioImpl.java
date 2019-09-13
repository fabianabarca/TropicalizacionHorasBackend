package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.ActividadDto;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.EstudianteDto;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ProyectosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

// TODO implementar
@Service
public class ProyectoServicioImpl implements ProyectoServicio {
    private ProyectosRepositorio proyectosRepositorio;

    @Autowired
    public ProyectoServicioImpl(ProyectosRepositorio proyectosRepositorio) {
        this.proyectosRepositorio = proyectosRepositorio;
    }

    @Override
    public void agregarProyecto(ProyectoEntidad proyectoEntidad) {
        this.proyectosRepositorio.save(proyectoEntidad);
    }

    @Override
    public void borrarProyecto(String proyectoNombre) {

    }

    @Override
    public void modificarDescripcionProyecto(String nombre, String nuevaDescripcion) {

    }

    @Override
    public Page<ProyectoEntidad> getProyectos(Integer pagina, Integer limite) {
        return this.proyectosRepositorio.findAll(PageRequest.of(pagina, limite));
    }

    @Override
    public ProyectoEntidad consultarProyectoPorId(String id) {
        return null;
    }

    @Override
    public Page<ActividadDto> actividadesProyecto(String nombre, Integer pagina, Integer limite) {
        return null;
    }

    @Override
    public Page<EstudianteDto> estudiantesProyecto(String nombre, Integer pagina, Integer limite) {
        return null;
    }

    @Override
    public void removerEstudiante(String proyecto, String estudiante) {

    }

    @Override
    public void agregarParticipante(String proyecto, String estudiante) {

    }
}
