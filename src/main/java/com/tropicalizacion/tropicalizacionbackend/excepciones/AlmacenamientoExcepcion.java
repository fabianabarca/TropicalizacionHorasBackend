package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

public class AlmacenamientoExcepcion extends ExcepcionGeneral {
    public AlmacenamientoExcepcion(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}
