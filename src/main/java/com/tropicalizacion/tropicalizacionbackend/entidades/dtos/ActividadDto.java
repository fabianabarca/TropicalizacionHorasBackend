package com.tropicalizacion.tropicalizacionbackend.entidades.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActividadDto {
    private String actividadCorreoEstudiante;
    private int actividadIdGenerado;
    private Date fecha;
    private int horas;
    private String estado;
    private String detalles;
    private String justificacionRechazo;
    private boolean decision;
    private String estudianteCorreoUsuario;
    private String categoriaNombre;
    private String proyectoNombre;
    private String revisorEntidad;
}
