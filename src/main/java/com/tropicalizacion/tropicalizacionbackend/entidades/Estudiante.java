package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "estudiante")
@Setter @Getter @Builder @NoArgsConstructor @AllArgsConstructor
public class Estudiante {
    @Id
    @Column(name = "pk_correo_usuario", length = 100)
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

    @Column(name = "carne", length = 6)
    @NotNull
    private String carne;

    @Column(name = "horas_totales")
    @NotNull
    private int horasTotales;

    @OneToOne
    @JoinColumn(name = "pk_correo_usuario", referencedColumnName = "pk_correo")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "estudiante_participa_proyecto",
            joinColumns = {@JoinColumn(name = "estudiante_correo")},
            inverseJoinColumns = {@JoinColumn(name = "proyecto_nombre")}
    )
    @NotNull
    private Set<Proyecto> proyectos = new HashSet<>();

    @OneToMany(mappedBy = "estudiante")
    private Set<Actividad> actividades = new HashSet<>();
}
