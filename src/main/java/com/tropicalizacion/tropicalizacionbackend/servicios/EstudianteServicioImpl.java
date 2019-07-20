package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioYaExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServicioImpl implements EstudianteServicio {

    private EstudiantesRepositorio estudiantesRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteServicioImpl(EstudiantesRepositorio estudiantesRepositorio, UsuariosRepositorio usuariosRepositorio, PasswordEncoder passwordEncoder){
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public void agregarEstudiante(EstudianteEntidad estudianteEntidad){
        if (usuariosRepositorio.findById(estudianteEntidad.getCorreoUsuario()).isPresent())
            throw new UsuarioYaExisteExcepcion("El correo " + estudianteEntidad.getCorreoUsuario() + " ya está tomado",
                    HttpStatus.CONFLICT, System.currentTimeMillis());

        // Codificar la contraseña dada, por ahora todas son "contrasenna", después el usuario tendrá que cambiarla
        String contasennaEncriptada = passwordEncoder.encode("contrasenna");
        estudianteEntidad.getUsuario().setContrasenna(contasennaEncriptada);

        // Guardar la entidad de usuario sin la relación de estudiante
        estudianteEntidad.getUsuario().setEstudiante(null);
        UsuarioEntidad usuarioGuardado = usuariosRepositorio.save(estudianteEntidad.getUsuario());
        estudianteEntidad.setUsuario(usuarioGuardado);

        // Guardar el estudiante, el correo se pone en null porque luego hibernate lo setea.
        estudianteEntidad.setCorreoUsuario(null);
        estudiantesRepositorio.save(estudianteEntidad);
    }

    public void borrarEstudiante(EstudianteEntidad EstudianteEntidad){

    }

    public void modificarEstudiante(EstudianteEntidad EstudianteEntidad){

    }

    public Page<EstudianteEntidad> getEstudiantes(Integer pagina, Integer limite){
        Page<EstudianteEntidad> EstudianteEntidadPage =  estudiantesRepositorio.findAll(PageRequest.of(pagina, limite));
        return EstudianteEntidadPage;
    }

    public EstudianteEntidad consultarEstudiantePorId(String id){
        EstudianteEntidad EstudianteEntidad = estudiantesRepositorio.findById(id).orElse(null);
        if(EstudianteEntidad != null){
            EstudianteEntidad.getProyectos().forEach(proyecto -> {
                proyecto.setEstudiantes(null);
                proyecto.setActividades(null);
            });
            EstudianteEntidad.getUsuario().setEstudiante(null);
        }
        return EstudianteEntidad;
    }
}
