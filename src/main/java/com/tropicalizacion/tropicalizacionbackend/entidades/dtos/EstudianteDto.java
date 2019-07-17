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
public class EstudianteDto {
    private String correoUsuario;
    private String tipo;
    private String estado;
    private Date fechaInicio;
    private Date fechaFinal;
    private String carne;
    private int horasTotales;
    private String usuarioTelefono;
    private String usuarioNombre;
    private String usuarioApellidos;
    private boolean usuarioHabilitado;
}
