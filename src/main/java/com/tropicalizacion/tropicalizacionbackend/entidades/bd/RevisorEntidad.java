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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "revisor")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class RevisorEntidad {
    @Id
    private String correoUsuario;

    @Column(name = "es_coordinador")
    @NotNull
    private boolean esCoordinador;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pk_correo_usuario", referencedColumnName = "pk_correo")
    private UsuarioEntidad usuario;

    @OneToMany(mappedBy = "revisor")
    private Set<ActividadEntidad> actividades = new HashSet<>();
}
