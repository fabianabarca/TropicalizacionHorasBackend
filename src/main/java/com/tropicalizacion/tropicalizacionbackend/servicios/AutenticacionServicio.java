package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.excepciones.MalasCredencialesExcepcion;
import org.springframework.stereotype.Service;

public interface AutenticacionServicio {
    /**
     * Valida las credenciales provistas y si son correctas devuelve el token para el usuario
     *
     * @param correo el correo del usuario a autenticar
     * @param contrasenna la contraseña del usuario a autenticar
     * @throws MalasCredencialesExcepcion si las credenciales no concuerdan con ningún usuario
     * @return el token del usuario
     */
    String autenticarUsuario(String correo, String contrasenna) throws MalasCredencialesExcepcion;
}
