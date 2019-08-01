package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioYaExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServicioImpl implements EstudianteServicio {
    private CorreosServicio correosServicio;
    private EstudiantesRepositorio estudiantesRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteServicioImpl(CorreosServicio correosServicio, EstudiantesRepositorio estudiantesRepositorio, UsuariosRepositorio usuariosRepositorio, PasswordEncoder passwordEncoder){
        this.correosServicio = correosServicio;
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
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

        UsuarioEntidad usuarioGuardado = usuariosRepositorio.save(estudianteEntidad.getUsuario());
        estudianteEntidad.setUsuario(usuarioGuardado);
        estudiantesRepositorio.save(estudianteEntidad);

        correosServicio.enviarContrasennaNueva(contrasennaNueva, estudianteEntidad.getUsuario().getCorreo());
    }

    public void borrarEstudiante(EstudianteEntidad EstudianteEntidad){

    }

    public void modificarEstudiante(EstudianteEntidad EstudianteEntidad){

    }

    public Page<EstudianteEntidad> getEstudiantes(Integer pagina, Integer limite){
        Page<EstudianteEntidad> estudianteEntidadPage =  estudiantesRepositorio.findAll(PageRequest.of(pagina, limite));
        return estudianteEntidadPage;
    }

    public EstudianteEntidad consultarEstudiantePorId(String id){
        EstudianteEntidad estudianteEntidad = estudiantesRepositorio.findById(id).orElse(null);
        return estudianteEntidad;
    }
}
