package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.repositorios.EstudiantesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EstudianteServicioImpl implements EstudianteServicio {

    private EstudiantesRepositorio estudiantesRepositorio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EstudianteServicioImpl(EstudiantesRepositorio estudiantesRepositorio, PasswordEncoder passwordEncoder){
        this.estudiantesRepositorio = estudiantesRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public void agregarEstudiante(EstudianteEntidad estudianteEntidad){
        String contasennaEncriptada = passwordEncoder.encode(estudianteEntidad.getUsuario().getContrasenna());
        estudianteEntidad.getUsuario().setContrasenna(contasennaEncriptada);
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
