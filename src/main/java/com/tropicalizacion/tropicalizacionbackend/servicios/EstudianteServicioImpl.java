package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.ProyectoNoExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioNoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioYaExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.ProyectosRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EstudianteServicioImpl implements EstudianteServicio {
    private CorreosServicio correosServicio;
    private EstudiantesRepositorio estudiantesRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private ProyectosRepositorio proyectosRepositorio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteServicioImpl(CorreosServicio correosServicio, EstudiantesRepositorio estudiantesRepositorio, UsuariosRepositorio usuariosRepositorio, ProyectosRepositorio proyectosRepositorio, PasswordEncoder passwordEncoder){
        this.correosServicio = correosServicio;
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.proyectosRepositorio = proyectosRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public void agregarEstudiante(EstudianteEntidad estudianteEntidad){
        if (usuariosRepositorio.findById(estudianteEntidad.getUsuario().getCorreo()).isPresent())
            throw new UsuarioYaExisteExcepcion("El correo " + estudianteEntidad.getCorreoUsuario() + " ya está tomado",
                    HttpStatus.CONFLICT, System.currentTimeMillis());

        // Codificar la contraseña dada, por ahora todas son "contrasenna", después el usuario tendrá que cambiarla
        String contrasennaNueva = RandomStringUtils.random(10, true, true);
        String contasennaEncriptada = passwordEncoder.encode(contrasennaNueva);
        estudianteEntidad.getUsuario().setContrasenna(contasennaEncriptada);
        estudianteEntidad.getUsuario().setActivado(false);
        estudianteEntidad.getUsuario().setBorrado(false);

        if (estudianteEntidad.getFechaFinal() == null) {
            estudianteEntidad.setFechaFinal(Date.valueOf(estudianteEntidad.getFechaInicio().toLocalDate().plusYears(1)));
        }

        UsuarioEntidad usuarioGuardado = usuariosRepositorio.save(estudianteEntidad.getUsuario());
        estudianteEntidad.setUsuario(usuarioGuardado);
        estudiantesRepositorio.save(estudianteEntidad);

        correosServicio.enviarContrasennaNueva(contrasennaNueva, estudianteEntidad.getUsuario().getCorreo());
    }

    public void borrarEstudiante(EstudianteEntidad estudianteEntidad){
        estudianteEntidad.setBorrado(true);
        estudianteEntidad.getUsuario().setBorrado(true);
        this.estudiantesRepositorio.save(estudianteEntidad);
    }

    public Page<EstudianteEntidad> getEstudiantes(Integer pagina, Integer limite){
        Page<EstudianteEntidad> estudianteEntidadPage = estudiantesRepositorio.findAll(PageRequest.of(pagina, limite));
        return estudianteEntidadPage;
    }

    public EstudianteEntidad consultarEstudiantePorId(String id){
        return estudiantesRepositorio.findById(id).orElseThrow(() ->
                new UsuarioNoEncontradoExcepcion("El estudiante con correo" + id + "no existe",
                        HttpStatus.NOT_FOUND,
                        System.currentTimeMillis()));
    }

    @Override
    public void editarProyectos(EstudianteEntidad estudianteEntidad, String[] proyectos) {
        Set<ProyectoEntidad> proyectosDeseados = Arrays.stream(proyectos)
                .map(p -> this.proyectosRepositorio.findById(p).orElseThrow(() -> new ProyectoNoExisteExcepcion("No existe " + p, HttpStatus.NOT_FOUND, System.currentTimeMillis())))
                .collect(Collectors.toSet());

        estudianteEntidad.getProyectos().stream()
                .filter(p -> !proyectosDeseados.contains(p))
                .forEach(p -> {
                    p.getEstudiantes().remove(estudianteEntidad);
                    this.proyectosRepositorio.save(p);
                });

        proyectosDeseados.stream()
                .filter(p -> !estudianteEntidad.getProyectos().contains(p))
                .forEach(p -> {
                    p.getEstudiantes().add(estudianteEntidad);
                    this.proyectosRepositorio.save(p);
                });

        estudianteEntidad.setProyectos(proyectosDeseados);
        this.estudiantesRepositorio.save(estudianteEntidad);
    }

    @Override
    public void editarEstudiante(EstudianteEntidad estudianteEntidad, EstudianteEntidad nuevoEstudiante) {
        estudianteEntidad.getUsuario().setApellidos(nuevoEstudiante.getUsuario().getApellidos());
        estudianteEntidad.getUsuario().setNombre(nuevoEstudiante.getUsuario().getNombre());
        estudianteEntidad.getUsuario().setTelefono(nuevoEstudiante.getUsuario().getTelefono());

        estudianteEntidad.setCarne(nuevoEstudiante.getCarne());
        estudianteEntidad.setTipo(nuevoEstudiante.getTipo());
        estudianteEntidad.setEstado(nuevoEstudiante.getEstado());
        estudianteEntidad.setHorasTotales(nuevoEstudiante.getHorasTotales());
        estudianteEntidad.setFechaInicio(nuevoEstudiante.getFechaInicio());
        estudianteEntidad.setFechaFinal(nuevoEstudiante.getFechaFinal());

        this.estudiantesRepositorio.save(estudianteEntidad);
    }
}
