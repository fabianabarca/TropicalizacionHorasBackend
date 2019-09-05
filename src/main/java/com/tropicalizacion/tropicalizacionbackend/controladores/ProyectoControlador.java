package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ProyectoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.ActividadDto;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.EstudianteDto;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.ProyectoDto;
import com.tropicalizacion.tropicalizacionbackend.servicios.ProyectoServicio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RestController
@RequestMapping(value = "/proyecto")
public class ProyectoControlador {
    private ProyectoServicio proyectoServicio;
    private ModelMapper modelMapper;

    @Autowired
    public ProyectoControlador(ProyectoServicio proyectoServicio, ModelMapper modelMapper) {
        this.proyectoServicio = proyectoServicio;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{nombre}")
    public ResponseEntity<CustomResponse> obtenerProyecto(@PathVariable String nombre) {
        ProyectoDto proyectoDto = this.modelMapper.map(
                this.proyectoServicio.consultarProyectoPorId(nombre),
                ProyectoDto.class);
        return ResponseEntity.ok(new CustomResponse("","", proyectoDto));
    }

    @GetMapping("/{nombre}/actividades")
    public ResponseEntity<CustomResponse> obtenerActividadesProyecto(@PathVariable String nombre, @RequestParam Integer pagina, @RequestParam Integer limite) {
        Page<ActividadDto> actividades = this.proyectoServicio.actividadesProyecto(nombre, pagina, limite);
        return ResponseEntity.ok(new CustomResponse("", "", actividades));
    }

    @GetMapping("/{nombre}/estudiantes")
    public ResponseEntity<CustomResponse> obtenerEstudiantesProyecto(@PathVariable String nombre, @RequestParam Integer pagina, @RequestParam Integer limite) {
        Page<EstudianteDto> estudiantes = this.proyectoServicio.estudiantesProyecto(nombre, pagina, limite);
        return ResponseEntity.ok(new CustomResponse("", "", estudiantes));
    }

    @GetMapping()
    public ResponseEntity<CustomResponse> obtenerProyectos(@RequestParam Integer pagina, @RequestParam Integer limite) {
        Page<ProyectoDto> proyectos = this.proyectoServicio.getProyectos(pagina, limite)
                .map(p -> this.modelMapper.map(p, ProyectoDto.class));
        return ResponseEntity.ok(new CustomResponse("", "", proyectos));
    }

    @PostMapping()
    public ResponseEntity<CustomResponse> crearProyecto(@RequestBody ProyectoDto proyecto) {
        ProyectoEntidad proyectoEntidad = this.modelMapper.map(proyecto, ProyectoEntidad.class);
        this.proyectoServicio.agregarProyecto(proyectoEntidad);
        return ResponseEntity.ok(new CustomResponse(""));
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<CustomResponse> borrarProyecto(@PathVariable String nombre) {
        this.proyectoServicio.borrarProyecto(nombre);
        return ResponseEntity.ok(new CustomResponse(""));
    }

    @DeleteMapping("/{proyecto}/participa/{estudiante}")
    public ResponseEntity<CustomResponse> borrarParticipa(@PathVariable String proyecto, @PathVariable String estudiante) {
        this.proyectoServicio.removerEstudiante(proyecto, estudiante);
        return ResponseEntity.ok(new CustomResponse(""));
    }

    @PutMapping("/{nombre}")
    public ResponseEntity<CustomResponse> actualizarDescripcion(@PathVariable String nombre, @RequestBody String nuevaDesc) {
        this.proyectoServicio.modificarDescripcionProyecto(nombre, nuevaDesc);
        return ResponseEntity.ok(new CustomResponse(""));
    }

    @PutMapping("/{proyecto}/participa/{estudiante}")
    public ResponseEntity<CustomResponse> asignarParticipa(@PathVariable String proyecto, @PathVariable String estudiante) {
        this.proyectoServicio.agregarParticipante(proyecto, estudiante);
        return ResponseEntity.ok(new CustomResponse(""));
    }
}
