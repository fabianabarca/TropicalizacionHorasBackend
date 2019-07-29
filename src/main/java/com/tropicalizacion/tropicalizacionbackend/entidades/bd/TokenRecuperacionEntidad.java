package com.tropicalizacion.tropicalizacionbackend.entidades.bd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "token_recuperacion")
@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class TokenRecuperacionEntidad {
    @Id
    private String correoUsuario;

    @Column(name = "token", length = 10)
    @NotNull
    private String token;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pk_correo_usuario", referencedColumnName = "pk_correo")
    private UsuarioEntidad usuario;
}
