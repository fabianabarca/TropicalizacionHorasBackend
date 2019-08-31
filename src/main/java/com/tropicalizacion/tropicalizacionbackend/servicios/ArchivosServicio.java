package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.ArchivoModelo;
import com.tropicalizacion.tropicalizacionbackend.entidades.UploadFileModelo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArchivosServicio {
    UploadFileModelo guardarArchivo(MultipartFile file, int idActividad);

    Resource cargarArchivoComoResource(String fileName, int idActividad);

    List<String> obtenerURIsActividad(int idActividad);

    void borrarArchivos(int idActividad);

    List<ArchivoModelo> crearArchivoEntidades(List<String> urIs);
}
