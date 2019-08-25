package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.ArchivoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchivosServicio {
    UploadFileResponse guardarArchivo(MultipartFile file, int idActividad);

    Resource cargarArchivoComoResource(String fileName, int idActividad);

    List<String> obtenerURIsActividad(int idActividad);

    void borrarArchivos(int idActividad);

    List<ArchivoEntidad> crearArchivoEntidades(List<String> urIs);
}
