package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidadPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActividadesRepositorio extends JpaRepository<ActividadEntidad, ActividadEntidadPK>  {
}
