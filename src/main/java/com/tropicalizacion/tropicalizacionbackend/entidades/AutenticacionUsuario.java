package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class AutenticacionUsuario {
    @NotBlank(message = "El campo de correo no puede estar vacío")
    @Email(message = "El correo digitado no está bien formado")
    private String correoUsuario;

    @NotBlank(message = "El campo de contraseña no puede estar vacío")
    private String contrasenna;
}
