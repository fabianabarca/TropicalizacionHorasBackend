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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "revisor")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class Revisor {
    @Id
    @Column(name = "pk_correo_usuario", length = 100)
    private String correoUsuario;

    @Column(name = "es_coordinador")
    @NotNull
    private boolean esCoordinador;

    @OneToOne
    @JoinColumn(name = "pk_correo_usuario", referencedColumnName = "pk_correo")
    private Usuario usuario;

    @OneToMany(mappedBy = "revisor")
    private Set<Actividad> actividades = new HashSet<>();
}
