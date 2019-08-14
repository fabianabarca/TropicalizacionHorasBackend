package com.tropicalizacion.tropicalizacionbackend.servicios;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ArchivosServicio {
    String guardarArchivo(MultipartFile file);

    Resource cargarArchivoComoResource(String fileName);
}
