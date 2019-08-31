package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioNoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private UsuariosRepositorio usuariosRepositorio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioServicioImpl(UsuariosRepositorio usuariosRepositorio, PasswordEncoder passwordEncoder){
        this.usuariosRepositorio = usuariosRepositorio;
        this.passwordEncoder = passwordEncoder;
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
        return usuarioEntidad;
    }

    @Override
    public void cambiarContrasenna(String correo, String contrasennaNueva) {
        UsuarioEntidad usuario = usuariosRepositorio.findById(correo).orElseThrow(() ->
                new UsuarioNoEncontradoExcepcion("No se encontró al usuario con el correo " + correo,
                        HttpStatus.NOT_FOUND,
                        System.currentTimeMillis()));

        if (!usuario.getActivado()) {
            usuario.setActivado(true);
        }

        usuario.setContrasenna(passwordEncoder.encode(contrasennaNueva));
        usuariosRepositorio.save(usuario);
    }
}
