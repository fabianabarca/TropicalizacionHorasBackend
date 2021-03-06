package com.tropicalizacion.tropicalizacionbackend.entidades.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDto {
    private String tipo;
    private String estado;
    private Date fechaInicio;
    private Date fechaFinal;
    private String carne;
    private int horasTotales;
    private Set<ProyectoSimpleDto> proyectos;
    private UsuarioDto usuario;
}
