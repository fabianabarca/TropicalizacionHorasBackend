package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.servicios.CategoriaServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> agregarCategoria(CategoriaEntidad categoriaEntidad){
        categoriaServicio.agregarCategoria(categoriaEntidad);
        return ok("Categoria agregada");
    }

    @GetMapping
    public ResponseEntity getCategorias(){
        Page<CategoriaEntidad> categoriaEntidadPage =  categoriaServicio.getCategorias(0, 10);
        return ok(categoriaEntidadPage);
    }
}
