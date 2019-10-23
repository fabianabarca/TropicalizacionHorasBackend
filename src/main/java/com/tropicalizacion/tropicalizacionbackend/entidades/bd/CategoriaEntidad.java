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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@SQLDelete(sql =
        "UPDATE CategoriaEntidad " +
                "SET borrado = true " +
                "WHERE id = ?")
@Loader(namedQuery = "findCategoriaById")
@NamedQuery(name = "findCategoriaById", query =
        "SELECT c " +
                "FROM CategoriaEntidad c " +
                "WHERE " +
                " c.nombre = ?1 AND " +
                " c.borrado = false")
@Where(clause = "borrado = false")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class CategoriaEntidad {
    @Id
    @Column(name = "pk_nombre", length = 40)
    private String nombre;

    @Column(name = "borrado")
    private boolean borrado;

    @OneToMany(mappedBy = "categoria")
    private Set<ActividadEntidad> actividades = new HashSet<>();
}
