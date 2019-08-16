package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.ActividadDto;
import com.tropicalizacion.tropicalizacionbackend.excepciones.NoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.servicios.ActividadServicioImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ArrayList<ActividadEntidad> actividadEntidadPage = actividadServicio.consultarActividadPorEstudiante(correo);
        ArrayList<ActividadDto> actividadDtoArrayList = new ArrayList<>();
        actividadEntidadPage.forEach(actividad -> {
            actividadDtoArrayList.add(modelMapper.map(actividad, ActividadDto.class));
        });
        return new ResponseEntity<>(new CustomResponse(actividadDtoArrayList), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> eliminarActividad(@PathVariable Integer id) {
        try {
            actividadServicio.borrarActividad(id);
        } catch (NoEncontradoExcepcion e) {
            return new ResponseEntity<CustomResponse>(new CustomResponse(e.getMessage()), e.getEstado());
        }
        catch (Exception e) {
            return new ResponseEntity<CustomResponse>(new CustomResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new CustomResponse(id), HttpStatus.OK);
    }
}