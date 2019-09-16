package com.tropicalizacion.tropicalizacionbackend.excepciones;


import org.springframework.http.HttpStatus;

public class ProyectoNoExisteExcepcion extends ExcepcionGeneral{
    public ProyectoNoExisteExcepcion(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}
