package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.ArchivoEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchivosServicio {
    UploadFileResponse guardarArchivo(MultipartFile file, int idActividad);

    Resource cargarArchivoComoResource(String fileName, int idActividad);

    List<ArchivoEntidad> obtenerArchivos(int idActividad);

    // Borra todos los archivos de una actividad
    void borrarArchivos(int idActividad);

    // Borra solo los archivos del array
    void borrarArchivos(int idActividad, String[] archivos);
}
