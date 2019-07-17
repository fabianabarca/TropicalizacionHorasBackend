package com.tropicalizacion.tropicalizacionbackend.entidades.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevisorDto {
    private String correoUsuario;
    private boolean esCoordinador;
    private String usuarioTelefono;
    private String usuarioNombre;
    private String usuarioApellidos;
    private boolean usuarioHabilitado;
}
