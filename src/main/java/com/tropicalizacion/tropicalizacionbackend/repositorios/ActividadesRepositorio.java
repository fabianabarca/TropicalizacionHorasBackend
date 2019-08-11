package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ActividadesRepositorio extends JpaRepository<ActividadEntidad, Integer>  {

    ArrayList<ActividadEntidad> findByEstudiante(EstudianteEntidad correo);
}
