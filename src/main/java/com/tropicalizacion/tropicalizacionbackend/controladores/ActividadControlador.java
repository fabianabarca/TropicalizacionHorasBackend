package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.ActividadDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.ActividadServicioImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@CrossOrigin
@RequestMapping(value = "/actividad")
@RestController
public class ActividadControlador {

    private ActividadServicioImpl actividadServicio;
    private ModelMapper modelMapper;

    @Autowired
    public ActividadControlador(ActividadServicioImpl actividadServicio, ModelMapper modelMapper){
        this.actividadServicio = actividadServicio;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarActividad(@RequestBody ActividadDto actividadDto){
        ActividadEntidad actividadEntidad = modelMapper.map(actividadDto, ActividadEntidad.class);
        actividadServicio.agregarActividad(actividadEntidad);
        return new ResponseEntity<>(new CustomResponse(""), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomResponse> consultarActividadesPorEstudiante(
            @RequestParam(value="correo", required=false) String correo){
        ArrayList<ActividadEntidad> actividadEntidadPage = actividadServicio.consultarActividadPorEstudiante(correo,0, 10);
        ArrayList<ActividadDto> actividadDtoArrayList = new ArrayList<>();
        actividadEntidadPage.forEach(actividad -> {
            actividadDtoArrayList.add(modelMapper.map(actividad, ActividadDto.class));
        });
        return new ResponseEntity<>(new CustomResponse(actividadDtoArrayList), HttpStatus.OK);
    }
}