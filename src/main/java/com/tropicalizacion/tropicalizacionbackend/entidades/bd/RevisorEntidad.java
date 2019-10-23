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
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "revisor")
@SQLDelete(sql =
        "UPDATE RevisorEntidad " +
                "SET borrado = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findRevisorById")
@NamedQuery(name = "findRevisorById", query =
        "SELECT r " +
                "FROM RevisorEntidad r " +
                "WHERE " +
                " r.correoUsuario= ?1 AND " +
                " r.borrado = false")
@Where(clause = "borrado = false")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class RevisorEntidad {
    @Id
    private String correoUsuario;

    @Column(name = "es_coordinador")
    @NotNull
    private boolean esCoordinador;

    @Column(name = "borrado")
    private boolean borrado;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pk_correo_usuario", referencedColumnName = "pk_correo")
    private UsuarioEntidad usuario;

    @OneToMany(mappedBy = "revisor")
    private Set<ActividadEntidad> actividades = new HashSet<>();
}
