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
    private int actividadIdGenerado;
    private Date fecha;
    private int horas;
    private String estado;
    private String detalles;
    private String justificacionRechazo;
    private char decision;
    private EstudianteDto estudiante;
    private CategoriaDto categoria;
    private ProyectoDto proyecto;
    private RevisorDto revisor;
}
