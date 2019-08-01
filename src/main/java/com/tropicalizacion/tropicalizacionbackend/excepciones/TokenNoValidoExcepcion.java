package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

public class TokenNoValidoExcepcion extends ExcepcionGeneral {
    public TokenNoValidoExcepcion(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}
