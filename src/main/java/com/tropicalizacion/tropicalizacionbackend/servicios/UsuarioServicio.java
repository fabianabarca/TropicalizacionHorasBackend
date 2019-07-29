package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import org.springframework.data.domain.Page;

public interface UsuarioServicio {

    void agregarUsuario(UsuarioEntidad usuarioEntidad);

    void borrarUsuario(UsuarioEntidad usuarioEntidad);

    void modificarUsuario(UsuarioEntidad usuarioEntidad);

    Page<UsuarioEntidad> getUsuarios(Integer pagina, Integer limite);

    UsuarioEntidad consultarUsuarioPorId(String id);

    /**
     * Cambia el campo de contrasenna de un usuario
     *
     * @param correo correo del usaurio a cambiar
     * @param contrasennaNueva contraseña nueva del usuario
     */
    void cambiarContrasenna(String correo, String contrasennaNueva);
}
