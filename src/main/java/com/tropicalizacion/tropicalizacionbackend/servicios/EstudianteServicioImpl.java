package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.EstudianteDto;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServicioImpl implements EstudianteServicio {

    private EstudiantesRepositorio estudiantesRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    @Autowired
    public EstudianteServicioImpl(EstudiantesRepositorio estudiantesRepositorio,
                                  UsuariosRepositorio usuariosRepositorio,
                                  PasswordEncoder passwordEncoder){
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public void agregarEstudiante(EstudianteEntidad estudianteEntidad){
        // Codificar la contraseña dada
        String contasennaEncriptada = passwordEncoder.encode(estudianteEntidad.getUsuario().getContrasenna());
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
        Page<EstudianteEntidad> estudianteEntidadPage =  estudiantesRepositorio.findAll(PageRequest.of(pagina, limite));
        return estudianteEntidadPage;
    }

    public EstudianteEntidad consultarEstudiantePorId(String id){
        EstudianteEntidad estudianteEntidad = estudiantesRepositorio.findById(id).orElse(null);
        return estudianteEntidad;
    }
}
