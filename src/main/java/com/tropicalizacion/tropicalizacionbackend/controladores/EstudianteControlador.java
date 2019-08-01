package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.EstudianteEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.EstudianteDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.EstudianteServicio;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
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
        List<EstudianteDto> EstudiantesNombre =  estudianteServicio.getEstudiantes(pagina, limite).stream()
                .map(estudiante -> modelMapper.map(estudiante, EstudianteDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new CustomResponse(EstudiantesNombre), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomResponse> getEstudiante(@PathVariable String id){
        EstudianteEntidad estudianteEntidad =  estudianteServicio.consultarEstudiantePorId(id);
        EstudianteDto estudianteDto = modelMapper.map(estudianteEntidad, EstudianteDto.class);
        estudianteDto.getUsuario().setCorreo(estudianteEntidad.getCorreoUsuario());
//        estudianteDto.getProyectos().clear();
//        estudianteEntidad.getProyectos().forEach(proyecto -> estudianteDto.getProyectos().add(proyecto.getNombre()));
        return new ResponseEntity<>(new CustomResponse(estudianteDto), HttpStatus.OK);
    }

}
