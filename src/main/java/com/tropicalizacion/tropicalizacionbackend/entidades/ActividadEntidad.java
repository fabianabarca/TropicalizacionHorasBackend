package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "actividad")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ActividadEntidad {
    @EmbeddedId
    private ActividadEntidadPK actividadEntidadPK;

    @Column(name = "fecha")
    @NotNull
    private Date fecha;

    @Column(name = "horas")
    @NotNull
    private int horas;

    @Column(name = "estado", columnDefinition = "ENUM('Pendiente', 'Aprobada', 'Rechazada')", length = 10)
    @NotNull
    private String estado;

    @Column(name = "detalles", columnDefinition = "MEDIUMTEXT")
    @NotNull
    private String detalles;

    @Column(name = "justificacion_rechazo", columnDefinition = "MEDIUMTEXT")
    private String justificacionRechazo;

    @Column(name = "decision")
    private boolean decision;

    @ManyToOne
    @MapsId("correoEstudiante")
    @JoinColumn(name = "fk_correo_estudiante", referencedColumnName = "pk_correo_usuario")
    private EstudianteEntidad estudiante;

    @ManyToOne
    @JoinColumn(name = "fk_nombre_categoria", referencedColumnName = "pk_nombre")
    @NotNull
    private CategoriaEntidad categoria;

    @ManyToOne
    @JoinColumn(name = "fk_nombre_proyecto", referencedColumnName = "pk_nombre")
    @NotNull
    private ProyectoEntidad proyecto;

    @ManyToOne
    @JoinColumn(name = "fk_correo_revisor", referencedColumnName = "pk_correo_usuario")
    private RevisorEntidad revisor;
}

