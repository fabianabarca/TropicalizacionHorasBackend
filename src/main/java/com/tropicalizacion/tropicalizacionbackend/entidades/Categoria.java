package com.tropicalizacion.tropicalizacionbackend.entidades;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Setter @Getter @Builder @AllArgsConstructor @NoArgsConstructor
public class Categoria {
    @Id
    @Column(name = "pk_nombre", length = 40)
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private Set<Actividad> actividades = new HashSet<>();
}
