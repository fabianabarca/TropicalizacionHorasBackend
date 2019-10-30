package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.EstudianteDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.EstudianteServicio;
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

@CrossOrigin
@RequestMapping(value = "/estudiante")
@RestController
public class EstudianteControlador {
    private EstudianteServicio estudianteServicio;
    private ModelMapper modelMapper;

    @Autowired
    public EstudianteControlador(EstudianteServicio EstudianteServicio, ModelMapper modelMapper){
        this.estudianteServicio = EstudianteServicio;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarEstudiante(@RequestBody EstudianteDto estudianteDto){
        estudianteServicio.agregarEstudiante(modelMapper.map(estudianteDto, EstudianteEntidad.class));
        return new ResponseEntity<>(new CustomResponse("El estudiante se agregó correctamente", ""), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getEstudiantes(
            @RequestParam Integer pagina,
            @RequestParam Integer limite
    ){
        Page<EstudianteDto> EstudiantesNombre =  estudianteServicio.getEstudiantes(pagina, limite)
                .map(e -> this.modelMapper.map(e, EstudianteDto.class));
        return new ResponseEntity<>(new CustomResponse(EstudiantesNombre), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomResponse> getEstudiante(@PathVariable String id){
        EstudianteEntidad EstudianteEntidad =  estudianteServicio.consultarEstudiantePorId(id);
        return new ResponseEntity<>(new CustomResponse(modelMapper.map(EstudianteEntidad, EstudianteDto.class)), HttpStatus.OK);
    }

    @GetMapping(value = "/{correo}/horas-pendientes")
    public ResponseEntity<CustomResponse> getHorasPendientes(@PathVariable String correo) {
        int horasPendientes = estudianteServicio.getHorasPendientes(correo);
        return new ResponseEntity<>(new CustomResponse(horasPendientes), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomResponse> editarEstudiante(@PathVariable String id, @RequestBody EstudianteEntidad nuevoEstudiante) {
        EstudianteEntidad estudianteEntidad = this.estudianteServicio.consultarEstudiantePorId(id);
        this.estudianteServicio.editarEstudiante(estudianteEntidad, nuevoEstudiante);
        return new ResponseEntity<>(new CustomResponse(HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping(value = "/proyectos/{email}")
    public ResponseEntity<CustomResponse> editarProyectos(@PathVariable String email, @RequestBody String[] proyectos) {
        EstudianteEntidad estudianteEntidad = this.estudianteServicio.consultarEstudiantePorId(email);
        this.estudianteServicio.editarProyectos(estudianteEntidad, proyectos);
        return new ResponseEntity<>(new CustomResponse(HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping(value = "{email}")
    public ResponseEntity<CustomResponse> borrarEstudiante(@PathVariable String email) {
        EstudianteEntidad estudianteEntidad = this.estudianteServicio.consultarEstudiantePorId(email);
        this.estudianteServicio.borrarEstudiante(estudianteEntidad);
        return new ResponseEntity<>(new CustomResponse(HttpStatus.OK), HttpStatus.OK);
    }
}
