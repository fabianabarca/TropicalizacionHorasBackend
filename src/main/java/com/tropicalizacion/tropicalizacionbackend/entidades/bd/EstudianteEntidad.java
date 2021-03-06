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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiante")
@SQLDelete(sql =
        "UPDATE EstudianteEntidad " +
                "SET borrado = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findEstudianteById")
@NamedQuery(name = "findEstudianteById", query =
        "SELECT e " +
                "FROM EstudianteEntidad e " +
                "WHERE " +
                " e.correoUsuario= ?1 AND " +
                " e.borrado = false")
@Where(clause = "borrado = false")
@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class EstudianteEntidad {

    @Id
    private String correoUsuario;

    @Column(name = "tipo", columnDefinition = "ENUM('Traslado', 'Regular', 'Pasantía')", length = 10)
    @NotNull
    private String tipo;

    @Column(name = "estado", columnDefinition = "ENUM('Activo', 'Prórroga', 'Finalizado', 'Inactivo')", length = 15)
    @NotNull
    private String estado;

    @Column(name = "fecha_inicio")
    @NotNull
    private Date fechaInicio;

    @Column(name = "fecha_final")
    @NotNull
    private Date fechaFinal;

    @Column(name = "carne", length = 6, unique = true)
    @NotNull
    private String carne;

    @Column(name = "horas_totales")
    @NotNull
    private int horasTotales;

    @Column(name = "borrado")
    private boolean borrado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pk_correo_usuario", referencedColumnName = "pk_correo")
    private UsuarioEntidad usuario;

    @ManyToMany
    @JoinTable(
            name = "estudiante_participa_proyecto",
            joinColumns = {@JoinColumn(name = "fk_estudiante_correo")},
            inverseJoinColumns = {@JoinColumn(name = "fk_proyecto_nombre")}
    )
    @NotNull
    private Set<ProyectoEntidad> proyectos = new HashSet<>();

    @OneToMany(mappedBy = "estudiante")
    private Set<ActividadEntidad> actividades = new HashSet<>();
}
