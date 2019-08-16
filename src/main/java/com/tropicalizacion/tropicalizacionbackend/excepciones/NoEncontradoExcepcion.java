package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

public class NoEncontradoExcepcion extends ExcepcionGeneral {

    public NoEncontradoExcepcion(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}
