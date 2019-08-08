package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidadPK;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ActividadesRepositorio extends JpaRepository<ActividadEntidad, ActividadEntidadPK>  {

    ArrayList<ActividadEntidad> findByActividadEntidadPKCorreoEstudiante(String correo);
}
