package com.tropicalizacion.tropicalizacionbackend.excepciones;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Clase de la que todas las excepciones custom van a heredar
 */
@Setter @Getter
class ExcepcionGeneral extends RuntimeException {
    private CustomResponse excepcionMensaje;
    private HttpStatus estado;

    ExcepcionGeneral(String mensaje, HttpStatus estado, long timestamp) {
        super(mensaje);
        this.estado = estado;
        this.excepcionMensaje = new CustomResponse("", mensaje, timestamp);
    }
}
