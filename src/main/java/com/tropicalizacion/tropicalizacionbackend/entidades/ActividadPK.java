package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ActividadPK implements Serializable {
    @Column(name = "correo_estudiante", length = 100)
    private String correoEstudiante;

    @Column(name = "id_generado")
    @GeneratedValue
    private int idGenerado;
}
