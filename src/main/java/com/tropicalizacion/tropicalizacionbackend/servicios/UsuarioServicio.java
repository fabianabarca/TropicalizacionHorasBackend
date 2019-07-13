package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface UsuarioServicio {

    void agregarUsuario(UsuarioEntidad usuarioEntidad);

    void borrarUsuario(UsuarioEntidad usuarioEntidad);

    void modificarUsuario(UsuarioEntidad usuarioEntidad);

    Page<UsuarioEntidad> getUsuarios(Integer pagina, Integer limite);

    UsuarioEntidad consultarUsuarioPorId(String id);
}
