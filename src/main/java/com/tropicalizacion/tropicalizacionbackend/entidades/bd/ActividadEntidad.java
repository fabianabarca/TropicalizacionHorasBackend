package com.tropicalizacion.tropicalizacionbackend.entidades.bd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "actividad")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ActividadEntidad {
    @Id
    @Column(name = "id_generado")
    @GeneratedValue(strategy = IDENTITY)
    private int idGenerado;

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

    @ManyToOne
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

    public void setValues(ActividadEntidad actividadEntidad){
        this.categoria = actividadEntidad.categoria;
        this.detalles = actividadEntidad.detalles;
        this.proyecto = actividadEntidad.proyecto;
        this.fecha = actividadEntidad.fecha;
        this.horas = actividadEntidad.horas;
        this.estado = actividadEntidad.estado;
        this.justificacionRechazo = actividadEntidad.justificacionRechazo;
    }
}

