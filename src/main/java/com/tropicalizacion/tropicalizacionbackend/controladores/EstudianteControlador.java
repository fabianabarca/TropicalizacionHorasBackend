package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.EstudianteDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.EstudianteServicioImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping(value = "/estudiante")
@RestController
public class EstudianteControlador {
    private EstudianteServicioImpl estudianteServicio;
    private ModelMapper modelMapper;

    @Autowired
    public EstudianteControlador(EstudianteServicioImpl EstudianteServicio, ModelMapper modelMapper){
        this.estudianteServicio = EstudianteServicio;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarEstudiante(@RequestBody EstudianteDto estudianteDto){
        estudianteServicio.agregarEstudiante(modelMapper.map(estudianteDto, EstudianteEntidad.class));
        return new ResponseEntity<>(new CustomResponse(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getEstudiantes(
            @RequestParam Integer pagina,
            @RequestParam Integer limite
    ){
        Page<EstudianteEntidad> EstudiantesNombre =  estudianteServicio.getEstudiantes(pagina, limite);
        return new ResponseEntity<>(new CustomResponse(EstudiantesNombre), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomResponse> getEstudiante(@PathVariable String id){
        EstudianteEntidad EstudianteEntidad =  estudianteServicio.consultarEstudiantePorId(id);
        return new ResponseEntity<>(new CustomResponse(EstudianteEntidad), HttpStatus.OK);
    }

}
