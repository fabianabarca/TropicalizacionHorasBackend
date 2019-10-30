package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.NoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ActividadesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.CategoriasRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ProyectosRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActividadServicioImpl implements ActividadServicio{

    private ActividadesRepositorio actividadesRepositorio;
    private EstudiantesRepositorio estudiantesRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private ProyectosRepositorio proyectosRepositorio;
    private CategoriasRepositorio categoriasRepositorio;

    @Autowired
    public ActividadServicioImpl(ActividadesRepositorio actividadesRepositorio,
                                 EstudiantesRepositorio estudiantesRepositorio,
                                 UsuariosRepositorio usuariosRepositorio, ProyectosRepositorio proyectosRepositorio,
                                 CategoriasRepositorio categoriasRepositorio
    ){
        this.actividadesRepositorio = actividadesRepositorio;
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.categoriasRepositorio = categoriasRepositorio;
        this.proyectosRepositorio = proyectosRepositorio;
    }

    public ActividadEntidad agregarActividad(ActividadEntidad actividadEntidad){
        EstudianteEntidad estudianteEntidad = estudiantesRepositorio.findById(actividadEntidad.getEstudiante().getUsuario().getCorreo())
                .orElseThrow(() ->
                        new NoEncontradoExcepcion("No se encontró el estudiante de correo: " + actividadEntidad.getEstudiante().getUsuario().getCorreo(),
                                HttpStatus.NOT_FOUND,
                                System.currentTimeMillis()));
        ProyectoEntidad proyectoEntidad = proyectosRepositorio.findById(actividadEntidad.getProyecto().getNombre())
                .orElseThrow(() ->
                        new NoEncontradoExcepcion("No se encontró el proyecto de nombre: " + actividadEntidad.getProyecto().getNombre(),
                                HttpStatus.NOT_FOUND,
                                System.currentTimeMillis()));
        CategoriaEntidad categoriaEntidad = categoriasRepositorio.findById(actividadEntidad.getCategoria().getNombre())
                .orElseThrow(() ->
                        new NoEncontradoExcepcion("No se encontró la categoría de nombre: " + actividadEntidad.getCategoria().getNombre(),
                                HttpStatus.NOT_FOUND,
                                System.currentTimeMillis()));
        actividadEntidad.setEstudiante(estudianteEntidad);
        actividadEntidad.setProyecto(proyectoEntidad);
        actividadEntidad.setCategoria(categoriaEntidad);
        return actividadesRepositorio.save(actividadEntidad);
    }

    public void borrarActividad(Integer id){
        ActividadEntidad actividadEntidad = actividadesRepositorio.findById(id).orElse(null);
        if (actividadEntidad == null){
            throw new NoEncontradoExcepcion("", HttpStatus.NOT_FOUND, System.currentTimeMillis());
        }
        try {
            actividadesRepositorio.delete(actividadEntidad);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void modificarActividad(Integer id, ActividadEntidad nuevaActividad, String userEmail){
        ActividadEntidad actividad = actividadesRepositorio.findById(id).orElseThrow(() ->
                new NoEncontradoExcepcion("No se encontró la actividad de id " + id,
                        HttpStatus.NOT_FOUND,
                        System.currentTimeMillis()));
        EstudianteEntidad estudiante = this.estudiantesRepositorio.findById(actividad.getEstudiante().getCorreoUsuario())
                .orElseThrow(() -> new NoEncontradoExcepcion("No se encontró al alumno de la actividad", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
        UsuarioEntidad usuarioHaciendoCambio = this.usuariosRepositorio.findById(userEmail)
                .orElseThrow(() -> new NoEncontradoExcepcion("No se encontró usuario con correo" + userEmail,
                                HttpStatus.NOT_FOUND,
                                System.currentTimeMillis()));


        String estadoPrevio = actividad.getEstado();
        int horasPrevias = estudiante.getHorasTotales();
        if ((estadoPrevio.equals("Pendiente") || estadoPrevio.equals("Rechazada") ) && nuevaActividad.getEstado().equals("Aprobada"))
            horasPrevias += actividad.getHoras();
        // Si el estudiante le hace un cambio cuando ya estaba aprobada
        else if (estadoPrevio.equals("Aprobada") && usuarioHaciendoCambio.getEstudiante() != null) {
            nuevaActividad.setEstado("Pendiente");
            horasPrevias -= actividad.getHoras();
        }
        else if (estadoPrevio.equals("Aprobada") && (nuevaActividad.getEstado().equals("Pendiente") || nuevaActividad.getEstado().equals("Rechazada")))
            horasPrevias -= actividad.getHoras();

        estudiante.setHorasTotales(horasPrevias);
        estudiantesRepositorio.save(estudiante);
        actividad.setValues(nuevaActividad);
        actividadesRepositorio.save(actividad);
    }

    // Can be optimized by using dynamic query https://javadeveloperzone.com/spring/spring-jpa-dynamic-query-example/
    public List<ActividadEntidad> getActividades(boolean aceptadas, boolean pendientes, boolean rechazadas){
        return this.actividadesRepositorio.findAll().stream()
            .filter(a -> {
                boolean pass = false;
                if (aceptadas)
                    pass = a.getEstado().equals("Aprobada");
                if (pendientes && !pass)
                    pass = a.getEstado().equals("Pendiente");
                if (rechazadas && !pass)
                    pass = a.getEstado().equals("Rechazada");
                return pass;
            })
        .collect(Collectors.toList());
    }

    public ArrayList<ActividadEntidad> consultarActividadPorEstudiante(String correoEstudiante){
        ArrayList<ActividadEntidad> actividadEntidadArrayList = actividadesRepositorio.findByEstudiante(EstudianteEntidad.builder().correoUsuario(correoEstudiante).build());
        return actividadEntidadArrayList;
    }

    public ActividadEntidad consultarActividadPorId(Integer id){
        return actividadesRepositorio.findById(id).orElseThrow(() ->
                new NoEncontradoExcepcion("No se encontró la actividad de id " + id,
                        HttpStatus.NOT_FOUND,
                        System.currentTimeMillis()));
    }
}
