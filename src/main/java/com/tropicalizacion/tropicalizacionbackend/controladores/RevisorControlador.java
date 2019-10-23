package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.RevisionModelo;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.RevisorEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.RevisorDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.RevisorServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO: implementar
@RestController
@RequestMapping("/revisor")
@CrossOrigin
public class RevisorControlador {

    private RevisorServicio revisorServicio;
    private ModelMapper modelMapper;

    @Autowired
    public RevisorControlador(RevisorServicio revisorServicio, ModelMapper modelMapper) {
        this.revisorServicio = revisorServicio;
        this.modelMapper = modelMapper;
    }

    // Devuelve también coordinadores
    @GetMapping()
    public ResponseEntity<CustomResponse> obtenerRevisores(@RequestParam Integer pagina, @RequestParam Integer limite){
        Page<RevisorDto> revisores = this.revisorServicio.getRevisores(pagina, limite).map(r -> this.modelMapper.map(r, RevisorDto.class));
        return new ResponseEntity<>(new CustomResponse(revisores), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> crearRevisor(@RequestBody RevisorDto revisor) {
        this.revisorServicio.agregarRevisor(this.modelMapper.map(revisor, RevisorEntidad.class));
        return new ResponseEntity<>(new CustomResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/{correo}")
    public  ResponseEntity<CustomResponse> borrarRevisor(@PathVariable String correo) {
        this.revisorServicio.borrarRevisor(correo);
        return new ResponseEntity<>(new CustomResponse(), HttpStatus.OK);
    }

    @PutMapping("/{correo}")
    public ResponseEntity<CustomResponse> modificarRevisor(@PathVariable String correo, @RequestBody RevisorDto revisor) {
        this.revisorServicio.modificarRevisor(this.modelMapper.map(revisor, RevisorEntidad.class));
        return new ResponseEntity<>(new CustomResponse(), HttpStatus.OK);
    }

    @PutMapping("/revisar/{actividadId}")
    public ResponseEntity<CustomResponse> revisarActividad(@PathVariable Integer actividadId, @RequestBody RevisionModelo revision) {
        return null;
    }
}
