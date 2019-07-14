package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.servicios.CategoriaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RequestMapping(value = "/categoria")
@RestController
public class CategoriaControlador {

    private CategoriaServicioImpl categoriaServicio;

    @Autowired
    public CategoriaControlador(CategoriaServicioImpl categoriaServicio){
        this.categoriaServicio = categoriaServicio;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarCategoria(CategoriaEntidad categoriaEntidad){
        categoriaServicio.agregarCategoria(categoriaEntidad);
        return new ResponseEntity<>(new CustomResponse(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getCategoriasNombre(){
        ArrayList<String> categoriasNombre =  categoriaServicio.getCategoriasNombre(0, 10);
        return new ResponseEntity<>(new CustomResponse(categoriasNombre), HttpStatus.OK);
    }
}
