package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class RecuperarContrasennaModelo {
    @Email
    @NotBlank
    private String correo;

    @NotBlank
    private String tokenRecuperacion;

    @NotBlank
    private String contrasennaNueva;
}
