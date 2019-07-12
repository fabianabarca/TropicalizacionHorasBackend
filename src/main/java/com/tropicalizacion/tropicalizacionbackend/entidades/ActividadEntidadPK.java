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
import java.util.Objects;

@Embeddable
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ActividadEntidadPK implements Serializable {
    @Column(name = "fk_correo_estudiante", length = 100)
    private String correoEstudiante;

    @Column(name = "id_generado")
    @GeneratedValue
    private int idGenerado;


    @Override
    public int hashCode() {
        return Objects.hash(correoEstudiante, idGenerado);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActividadEntidadPK that = (ActividadEntidadPK) o;
        return Objects.equals(correoEstudiante, that.correoEstudiante) &&
                Objects.equals(idGenerado, that.idGenerado);
    }
}
