package com.tropicalizacion.tropicalizacionbackend.servicios;

public interface TokenRecuperacionServicio {
    /**
     * Genera un nuevo token de recuperación para el correo dado y lo guarda en la bd.
     * También envía el token por correo al usuario.
     *
     * @param correo correo del usuario que pidió la recuperación
     * @return retorna el token
     */
    public String generarToken(String correo);

    /**
     * Valida que el token dado sea el mismo que el guardado para el usuario indicado.
     * Si lo son, borra la entrada de la bd
     *
     * @param correo correo del usuario que se está validando
     * @param token token digitado por el usuario
     */
    public void validarYBorrarToken(String correo, String token);
}
