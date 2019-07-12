package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

/**
 * Clase con el contenido de información de una excepción
 */
public class ExcepcionMensaje {
    private HttpStatus estado;
    private String mensaje;
    private long timestamp;

    ExcepcionMensaje(HttpStatus estado, String mensaje, long timestamp) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.timestamp = timestamp;
    }

    public ExcepcionMensaje() {
    }

    HttpStatus getEstado() {
        return estado;
    }

    public void setEstado(HttpStatus estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
