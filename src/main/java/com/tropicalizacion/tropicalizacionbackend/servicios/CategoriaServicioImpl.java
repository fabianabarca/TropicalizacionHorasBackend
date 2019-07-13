package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.repositorios.CategoriasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private CategoriasRepositorio categoriasRepositorio;

    @Autowired
    public CategoriaServicioImpl(CategoriasRepositorio categoriasRepositorio){
        this.categoriasRepositorio = categoriasRepositorio;
    }

    public void agregarCategoria(CategoriaEntidad categoriaEntidad){
        categoriasRepositorio.save(categoriaEntidad);
    }

    public void borrarCategoria(CategoriaEntidad categoriaEntidad){

    }

    public void modificarCategoria(CategoriaEntidad categoriaEntidad){

    }

    public Page<CategoriaEntidad> getCategorias(Integer pagina, Integer limite){
        return categoriasRepositorio.findAll(PageRequest.of(pagina, limite));
    }
}
