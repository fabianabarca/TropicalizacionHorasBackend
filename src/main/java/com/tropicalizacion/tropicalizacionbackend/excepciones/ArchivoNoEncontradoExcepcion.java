package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

public class ArchivoNoEncontradoExcepcion extends ExcepcionGeneral {
    public ArchivoNoEncontradoExcepcion(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}
