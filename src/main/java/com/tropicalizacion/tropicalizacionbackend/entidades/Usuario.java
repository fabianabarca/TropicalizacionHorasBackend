package com.tropicalizacion.tropicalizacionbackend.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Column
    @Id
    private String correo;

    @Column
    private String contrasenna;

    @Column
    private String telefono;

    @Column
    private String nombre;

    @Column
    private String apellidos;
}
