package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class CambiarContrasennaModelo {
    private String contraVieja;
    private String contraNueva;
}
