package com.tropicalizacion.tropicalizacionbackend.entidades.bd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Loader;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@SQLDelete(sql =
        "UPDATE UsuarioEntidad " +
                "SET borrado = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findUsuarioById")
@NamedQuery(name = "findUsuarioById", query =
        "SELECT u " +
                "FROM UsuarioEntidad u " +
                "WHERE " +
                " u.correo = ?1 AND " +
                " u.borrado = false")
@Where(clause = "borrado = false")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class UsuarioEntidad {
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

    @Column(name = "activado")
    @NotNull
    private Boolean activado;

    @Column(name = "borrado")
    @NotNull
    private Boolean borrado;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private EstudianteEntidad estudiante;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private RevisorEntidad revisor;
}
