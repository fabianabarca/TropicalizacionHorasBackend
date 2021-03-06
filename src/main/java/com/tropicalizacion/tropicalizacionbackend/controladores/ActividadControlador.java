package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.ArchivoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.ActividadEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.dtos.ActividadDto;
import com.tropicalizacion.tropicalizacionbackend.excepciones.NoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.servicios.ActividadServicio;
import com.tropicalizacion.tropicalizacionbackend.servicios.ArchivosServicio;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RequestMapping(value = "/actividad")
@RestController
public class ActividadControlador {

    private ActividadServicio actividadServicio;
    private ModelMapper modelMapper;
    private ArchivosServicio archivosServicio;
    private Logger logger = LoggerFactory.getLogger(ActividadControlador.class);

    @Autowired
    public ActividadControlador(ActividadServicio actividadServicio, ModelMapper modelMapper, ArchivosServicio archivosServicio){
        this.actividadServicio = actividadServicio;
        this.modelMapper = modelMapper;
        this.archivosServicio = archivosServicio;
    }

    @PostMapping
    public ResponseEntity<CustomResponse> agregarActividad(@RequestBody ActividadDto actividadDto){
        ActividadEntidad actividadEntidad = modelMapper.map(actividadDto, ActividadEntidad.class);
        try {
            final ActividadEntidad actividadGuardada = actividadServicio.agregarActividad(actividadEntidad);
            return new ResponseEntity<>(new CustomResponse("", "", actividadGuardada.getIdGenerado()), HttpStatus.OK);
        } catch (NoEncontradoExcepcion e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<CustomResponse> consultarActividades(@RequestParam(value="aceptadas") boolean aceptadas,
                                                               @RequestParam(value = "pendientes") boolean pendientes,
                                                               @RequestParam(value = "rechazadas") boolean rechazadas) {
        List<ActividadDto> actividades = this.actividadServicio.getActividades(aceptadas, pendientes, rechazadas).stream()
            .map(a -> this.modelMapper.map(a, ActividadDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new CustomResponse(actividades), HttpStatus.OK);
    }

    @GetMapping("/estudiante")
    public ResponseEntity<CustomResponse> consultarActividadesPorEstudiante(
            @RequestParam(value="correo", required=false) String correo){
        ArrayList<ActividadEntidad> actividadEntidadPage = actividadServicio.consultarActividadPorEstudiante(correo);
        ArrayList<ActividadDto> actividadDtoArrayList = new ArrayList<>();
        actividadEntidadPage.forEach(actividad -> {
            actividadDtoArrayList.add(modelMapper.map(actividad, ActividadDto.class));
        });
        return new ResponseEntity<>(new CustomResponse(actividadDtoArrayList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getActividad(@PathVariable Integer id) {
        try {
            final ActividadEntidad actividadEntidad = actividadServicio.consultarActividadPorId(id);
            final ActividadDto actividadDto = modelMapper.map(actividadEntidad, ActividadDto.class);
            return new ResponseEntity<>(new CustomResponse(actividadDto), HttpStatus.OK);
        } catch (NoEncontradoExcepcion e){
            return new ResponseEntity<CustomResponse>(new CustomResponse(e.getMessage()), e.getEstado());
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse> modificarActividad(@PathVariable Integer id, @RequestBody ActividadDto actividadDto, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(new CustomResponse("No hay usuario autenticado"), HttpStatus.UNAUTHORIZED);
        }
        final ActividadEntidad actividadEntidad = modelMapper.map(actividadDto, ActividadEntidad.class);
        actividadServicio.modificarActividad(id, actividadEntidad, principal.getName());
        return new ResponseEntity<>(new CustomResponse(""), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> eliminarActividad(@PathVariable Integer id) {
        try {
            archivosServicio.borrarArchivos(id);
            actividadServicio.borrarActividad(id);
        } catch (NoEncontradoExcepcion e) {
            return new ResponseEntity<CustomResponse>(new CustomResponse(e.getMessage()), e.getEstado());
        }
        catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new CustomResponse("Actividad borrada", ""));
    }

    @PostMapping("/archivo/{idActividad}")
    public List<CustomResponse> subirArchivos(@PathVariable int idActividad, @RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(file -> this.archivosServicio.guardarArchivo(file, idActividad))
                .map(fileResponse -> new CustomResponse("","", fileResponse))
                .collect(Collectors.toList());
    }

    @GetMapping("/archivo/{idActividad}")
    public ResponseEntity<CustomResponse> obtenerURIsActividad(@PathVariable int idActividad) {
            List<ArchivoEntidad> archivos = this.archivosServicio.obtenerArchivos(idActividad);
            return ResponseEntity.ok(new CustomResponse("","", archivos));
    }

    @GetMapping("/archivo/{idActividad}/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int idActividad, @PathVariable String fileName, HttpServletRequest request) {
        Resource resource = this.archivosServicio.cargarArchivoComoResource(fileName, idActividad);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            this.logger.info("No se logró determinar el tipo del archivo " + fileName);
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/archivo/{idActividad}")
    public ResponseEntity<CustomResponse> deleteFiles(@PathVariable int idActividad, @RequestBody String[] archivos) {
        this.archivosServicio.borrarArchivos(idActividad, archivos);
        return ResponseEntity.ok(new CustomResponse("Archivos borrados exitósamente", "", null));
    }
}