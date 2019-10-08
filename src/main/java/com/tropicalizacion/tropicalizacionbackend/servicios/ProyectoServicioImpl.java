package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.NoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.ProyectoNoExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ProyectosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return this.proyectosRepositorio.findById(id).orElseThrow(() -> new ProyectoNoExisteExcepcion("El proyecto " + id + "no existe", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
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
    public void removerEstudiante(String nombreProyecto, String correoEstudiante) {
        ProyectoEntidad proyecto = this.proyectosRepositorio.findById(nombreProyecto).orElseThrow(() -> new ProyectoNoExisteExcepcion("El proyecto " + nombreProyecto + "no existe", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
        EstudianteEntidad estudiante = this.estudiantesRepositorio.findById(correoEstudiante).orElseThrow(() -> new NoEncontradoExcepcion("No existe estudiante con correo " + correoEstudiante, HttpStatus.NOT_FOUND, System.currentTimeMillis()));

        proyecto.getEstudiantes().removeIf(estudianteP -> estudianteP.getCorreoUsuario().equals(correoEstudiante));
        estudiante.getProyectos().removeIf(proyectoE -> proyectoE.getNombre().equals(nombreProyecto));

        this.proyectosRepositorio.save(proyecto);
    }

    @Override
    public void agregarParticipante(String nombreProyecto, String[] correosEstudiantes) {
        ProyectoEntidad proyecto = this.proyectosRepositorio.findById(nombreProyecto).orElseThrow(() -> new ProyectoNoExisteExcepcion("El proyecto " + nombreProyecto + "no existe", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
        List<EstudianteEntidad> estudiantes = Arrays.stream(correosEstudiantes)
                .map(correo -> estudiantesRepositorio.findById(correo).orElseThrow(() -> new NoEncontradoExcepcion("No existe estudiante con correo " + correo, HttpStatus.NOT_FOUND, System.currentTimeMillis())))
                .collect(Collectors.toList());

        estudiantes.forEach(estudianteEntidad -> estudianteEntidad.getProyectos().add(proyecto));
        proyecto.getEstudiantes().addAll(estudiantes);

        this.proyectosRepositorio.save(proyecto);
    }
}
