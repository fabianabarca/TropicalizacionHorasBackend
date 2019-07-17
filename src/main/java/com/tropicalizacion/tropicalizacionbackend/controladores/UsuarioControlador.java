package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.servicios.UsuarioServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(value = "/usuario")
@RestController
public class UsuarioControlador {
    private UsuarioServicioImpl usuarioServicio;

    @Autowired
    public UsuarioControlador(UsuarioServicioImpl usuarioServicio){
        this.usuarioServicio = usuarioServicio;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarUsuario(UsuarioEntidad UsuarioEntidad){
        usuarioServicio.agregarUsuario(UsuarioEntidad);
        return new ResponseEntity<>(new CustomResponse(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getUsuarios(
            @RequestParam Integer pagina,
            @RequestParam Integer limite
    ){
        Page<UsuarioEntidad> UsuariosNombre =  usuarioServicio.getUsuarios(pagina, limite);
        return new ResponseEntity<>(new CustomResponse(UsuariosNombre), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomResponse> getUsuario(@PathVariable String id){
        UsuarioEntidad usuarioEntidad =  usuarioServicio.consultarUsuarioPorId(id);
        return new ResponseEntity<>(new CustomResponse(usuarioEntidad), HttpStatus.OK);
    }
}
