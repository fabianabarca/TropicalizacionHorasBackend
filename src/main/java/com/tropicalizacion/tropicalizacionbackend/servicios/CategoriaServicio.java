package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface CategoriaServicio {

    void agregarCategoria(CategoriaEntidad categoriaEntidad);

    void borrarCategoria(CategoriaEntidad categoriaEntidad);

    void modificarCategoria(CategoriaEntidad categoriaEntidad);

    Page<CategoriaEntidad> getCategorias(Integer pagina, Integer limite);
}
