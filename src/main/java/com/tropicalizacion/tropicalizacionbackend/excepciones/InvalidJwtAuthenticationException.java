package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

public class InvalidJwtAuthenticationException extends ExcepcionGeneral {
    public InvalidJwtAuthenticationException(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}