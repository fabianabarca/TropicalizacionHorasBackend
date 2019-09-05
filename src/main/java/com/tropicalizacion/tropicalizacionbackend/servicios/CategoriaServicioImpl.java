package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.CategoriaNoExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.CategoriasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private CategoriasRepositorio categoriasRepositorio;

    @Autowired
    public CategoriaServicioImpl(CategoriasRepositorio categoriasRepositorio){
        this.categoriasRepositorio = categoriasRepositorio;
    }

    public void agregarCategoria(CategoriaEntidad categoriaEntidad){
        categoriaEntidad.setBorrado(false);
        categoriasRepositorio.save(categoriaEntidad);
    }

    public void borrarCategoria(String categoriaNombre){
        CategoriaEntidad categoria = this.categoriasRepositorio.findById(categoriaNombre)
                .orElseThrow(() ->
                        new CategoriaNoExisteExcepcion("La categoria " + categoriaNombre + " no existe",
                        HttpStatus.NOT_FOUND,
                        System.currentTimeMillis()));
        categoria.setBorrado(true);
        this.categoriasRepositorio.save(categoria);
    }


    public ArrayList<String> getCategoriasNombre(){
        List<CategoriaEntidad> categorias =  categoriasRepositorio.findAll()
                .stream()
                .filter(categoria -> !categoria.isBorrado())
                .collect(Collectors.toList());
        ArrayList<String> categoriasNombre = new ArrayList<>();
        categorias.forEach(categoria -> categoriasNombre.add(categoria.getNombre()));
        return categoriasNombre;
    }
}
