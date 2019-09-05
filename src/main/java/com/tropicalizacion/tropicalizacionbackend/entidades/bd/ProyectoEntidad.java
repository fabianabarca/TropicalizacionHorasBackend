package com.tropicalizacion.tropicalizacionbackend.entidades.bd;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "proyecto")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class ProyectoEntidad {
    @Id
    @Column(name = "pk_nombre", length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "MEDIUMTEXT")
    private String descripcion;

    @Column(name = "borrado")
    private boolean borrado;

    @ManyToMany(mappedBy = "proyectos")
    private Set<EstudianteEntidad> estudiantes = new HashSet<>();

    @OneToMany(mappedBy = "proyecto")
    private Set<ActividadEntidad> actividades = new HashSet<>();
}
