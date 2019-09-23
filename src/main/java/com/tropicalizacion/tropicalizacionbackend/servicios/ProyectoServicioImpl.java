package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.ProyectoNoExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ProyectosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

// TODO implementar
@Service
public class ProyectoServicioImpl implements ProyectoServicio {
    private ProyectosRepositorio proyectosRepositorio;
    private EstudiantesRepositorio estudiantesRepositorio;

    @Autowired
    public ProyectoServicioImpl(ProyectosRepositorio proyectosRepositorio, EstudiantesRepositorio estudiantesRepositorio) {
        this.proyectosRepositorio = proyectosRepositorio;
        this.estudiantesRepositorio = estudiantesRepositorio;
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
        ProyectoEntidad proyecto = this.proyectosRepositorio.findById(nombre)
                .orElseThrow(() -> new ProyectoNoExisteExcepcion("El proyecto " + nombre + "no existe", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
        proyecto.setDescripcion(nuevaDescripcion);
        this.proyectosRepositorio.save(proyecto);
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
    public Page<EstudianteEntidad> estudiantesProyecto(String nombre, Integer pagina, Integer limite) {
        return this.estudiantesRepositorio.estudiantesDeProyecto(nombre, PageRequest.of(pagina, limite));
    }


    @Override
    public Page<EstudianteEntidad> estudiantesNoEnProyecto(String nombre, Integer pagina, Integer limite) {
        return this.estudiantesRepositorio.estudiantesNoEnProyecto(nombre, PageRequest.of(pagina, limite));
    }

    @Override
    public void removerEstudiante(String proyecto, String estudiante) {

    }

    @Override
    public void agregarParticipante(String proyecto, String estudiante) {

    }
}
