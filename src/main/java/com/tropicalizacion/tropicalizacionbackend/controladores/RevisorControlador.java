package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.RevisionModelo;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.RevisorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: implementar
@RestController
@RequestMapping("/revisor")
@CrossOrigin
public class RevisorControlador {

    @GetMapping("/pendientes")
    public ResponseEntity<CustomResponse> obtenerActividadesPendientes() {

        return null;
    }

    // Devuelve también coordinadores
    @GetMapping()
    public ResponseEntity<CustomResponse> obtenerRevisores(){
        return null;
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> crearRevisor(@RequestBody RevisorDto revisor) {
        return null;
    }

    @PostMapping("/coordinador")
    public ResponseEntity<CustomResponse> crearCoordinador(@RequestBody RevisorDto coordinador) {
        return null;
    }

    @DeleteMapping("/{correo}")
    public  ResponseEntity<CustomResponse> borrarRevisor(@PathVariable String correo) {
        return null;
    }

    @DeleteMapping("/coordinador/{correo}")
    public  ResponseEntity<CustomResponse> borrarCoordinador(@PathVariable String correo) {
        return null;
    }

    @PutMapping("/revisar/{actividadId}")
    public ResponseEntity<CustomResponse> revisarActividad(@PathVariable Integer actividadId, @RequestBody RevisionModelo revision) {
        return null;
    }
}
