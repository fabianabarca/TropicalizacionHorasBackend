package com.tropicalizacion.tropicalizacionbackend.entidades.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class UsuarioDto {
    private String correo;
    private String telefono;
    private String nombre;
    private String apellidos;
    private boolean habilitado;
}
