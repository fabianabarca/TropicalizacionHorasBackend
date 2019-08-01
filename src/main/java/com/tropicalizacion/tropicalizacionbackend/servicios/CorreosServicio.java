package com.tropicalizacion.tropicalizacionbackend.servicios;

public interface CorreosServicio {
    void enviarContrasennaNueva(String contrasenna, String correo);

    void enviarCorreoGenerico(String correo, String motivo, String texto);
}
