package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import java.util.ArrayList;

public interface CategoriaServicio {

    void agregarCategoria(CategoriaEntidad categoriaEntidad);

    void borrarCategoria(CategoriaEntidad categoriaEntidad);

    void modificarCategoria(CategoriaEntidad categoriaEntidad);

    ArrayList<String> getCategoriasNombre();
}
