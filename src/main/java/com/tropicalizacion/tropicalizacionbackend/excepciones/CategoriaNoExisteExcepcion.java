package com.tropicalizacion.tropicalizacionbackend.excepciones;

import org.springframework.http.HttpStatus;

/**
 * Author: jdvar
 * Date: 9/5/2019
 * Time: 3:23 PM
 **/
public class CategoriaNoExisteExcepcion extends ExcepcionGeneral {
    public CategoriaNoExisteExcepcion(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje, estado, timestamp);
    }
}
