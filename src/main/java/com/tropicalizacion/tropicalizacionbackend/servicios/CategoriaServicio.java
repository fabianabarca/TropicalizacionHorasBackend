package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import java.util.ArrayList;

public interface CategoriaServicio {

    void agregarCategoria(CategoriaEntidad categoriaEntidad);

    void borrarCategoria(String categoriaNombre);

    ArrayList<String> getCategoriasNombre();
}
