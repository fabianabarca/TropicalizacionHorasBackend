package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.UsuarioDto;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuariosRepositorio usuariosRepositorio;
    private ModelMapper modelMapper;

    @Autowired
    public UsuarioServicioImpl(UsuariosRepositorio usuariosRepositorio, ModelMapper modelMapper){
        this.usuariosRepositorio = usuariosRepositorio;
        this.modelMapper = modelMapper;
    }

    public void agregarUsuario(UsuarioEntidad usuarioEntidad){

    }

    public void borrarUsuario(UsuarioEntidad usuarioEntidad){

    }

    public void modificarUsuario(UsuarioEntidad usuarioEntidad){

    }

    public Page<UsuarioEntidad> getUsuarios(Integer pagina, Integer limite){
        Page<UsuarioEntidad> usuarioEntidadPage =  usuariosRepositorio.findAll(PageRequest.of(pagina, limite));
        return usuarioEntidadPage;
    }

    public UsuarioEntidad consultarUsuarioPorId(String id){
        UsuarioEntidad usuarioEntidad = usuariosRepositorio.findById(id).orElse(null);
        UsuarioDto usuarioDto = modelMapper.map(usuarioEntidad, UsuarioDto.class);
        if(usuarioEntidad != null){
            usuarioEntidad.setEstudiante(null);
            usuarioEntidad.setRevisor(null);
            usuarioEntidad.setContrasenna(null);
        }
        return usuarioEntidad;
    }
}
