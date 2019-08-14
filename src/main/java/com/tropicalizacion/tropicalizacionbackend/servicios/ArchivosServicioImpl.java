package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.excepciones.AlmacenamientoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.ArchivoNoEncontradoExcepcion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ArchivosServicioImpl implements ArchivosServicio {
    private Path fileStorageLocation;

    public ArchivosServicioImpl(@Value("${file.upload-dir}") String uploadPath) {
        this.fileStorageLocation = Paths.get(uploadPath)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new AlmacenamientoExcepcion("No se logró abrir el directorio de almacenamiento", HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis());
        }
    }

    public String guardarArchivo(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new AlmacenamientoExcepcion("El archivo tiene una secuencia de caracteres inválida:  " + fileName, HttpStatus.BAD_REQUEST, System.currentTimeMillis());
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new AlmacenamientoExcepcion("No se logró almacenar el archivo: " + fileName, HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis());
        }
    }

    @GetMapping("descargarArchivo")
    public Resource cargarArchivoComoResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new ArchivoNoEncontradoExcepcion("Archivo no encontrado " + fileName, HttpStatus.NOT_FOUND, System.currentTimeMillis());
            }
        } catch (MalformedURLException ex) {
            throw new ArchivoNoEncontradoExcepcion("Archivo no encontrado " + fileName, HttpStatus.NOT_FOUND, System.currentTimeMillis());
        }
    }
}
