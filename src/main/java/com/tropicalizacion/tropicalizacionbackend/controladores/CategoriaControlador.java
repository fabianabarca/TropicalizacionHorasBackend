package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.CategoriaEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.CategoriaDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.CategoriaServicioImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RequestMapping(value = "/categoria")
@RestController
public class CategoriaControlador {

    private CategoriaServicioImpl categoriaServicio;
    private ModelMapper modelMapper;

    @Autowired
    public CategoriaControlador(CategoriaServicioImpl categoriaServicio, ModelMapper modelMapper){
        this.categoriaServicio = categoriaServicio;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarCategoria(@RequestBody  CategoriaDto categoriaEntidad){
        categoriaServicio.agregarCategoria(this.modelMapper.map(categoriaEntidad, CategoriaEntidad.class));
        return new ResponseEntity<>(new CustomResponse(""), HttpStatus.OK);
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<CustomResponse> borrarCategoria(@PathVariable String nombre) {
        categoriaServicio.borrarCategoria(nombre);
        return new ResponseEntity<>(new CustomResponse(""), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getCategoriasNombre(){
        ArrayList<String> categoriasNombre =  categoriaServicio.getCategoriasNombre();
        return new ResponseEntity<>(new CustomResponse(categoriasNombre), HttpStatus.OK);
    }
}
