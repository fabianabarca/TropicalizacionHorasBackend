package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class Usuario {
    @Id
    @Column(length = 100, name = "pk_correo")
    private String correo;

    @Column(length=60, name = "contrasenna")
    @NotNull
    private String contrasenna;

    @Column(length = 8, name = "telefono")
    @NotNull
    private String telefono;

    @Column(length = 20, name = "nombre")
    @NotNull
    private String nombre;

    @Column(length = 40, name = "apellidos")
    @NotNull
    private String apellidos;

    @OneToOne(mappedBy = "correoUsuario")
    private Estudiante estudiante;

    @OneToOne(mappedBy = "correoUsuario")
    private Revisor revisor;
}