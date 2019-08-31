package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.ArchivoModelo;
import com.tropicalizacion.tropicalizacionbackend.entidades.UploadFileModelo;
import com.tropicalizacion.tropicalizacionbackend.excepciones.ActividadNoExiste;
import com.tropicalizacion.tropicalizacionbackend.excepciones.AlmacenamientoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.ArchivoNoEncontradoExcepcion;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArchivosServicioImpl implements ArchivosServicio {
    private Path fileStorageLocation;
    private ActividadServicio actividadServicio;

    @Autowired
    public ArchivosServicioImpl(@Value("${file.upload-dir}") String uploadPath, ActividadServicio actividadServicio) {
        this.fileStorageLocation = Paths.get(uploadPath)
                .toAbsolutePath().normalize();
        this.actividadServicio = actividadServicio;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new AlmacenamientoExcepcion("No se logró abrir el directorio de almacenamiento", HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis());
        }
    }

    @Override
    public UploadFileModelo guardarArchivo(MultipartFile file, int idActividad) {
        if (this.actividadServicio.consultarActividadPorId(idActividad) == null)
            throw new ActividadNoExiste("No existe actividad con el id " + idActividad, HttpStatus.NOT_FOUND, System.currentTimeMillis());

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new AlmacenamientoExcepcion("El archivo tiene una secuencia de caracteres inválida:  " + fileName, HttpStatus.BAD_REQUEST, System.currentTimeMillis());
            }

            Path targetLocation = this.fileStorageLocation.resolve(String.valueOf(idActividad));
            if(!Files.exists(targetLocation)) {
                new File(targetLocation.toString()).mkdir();
            }
            Files.copy(file.getInputStream(), targetLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            return new UploadFileModelo(fileName, this.createURI(fileName, idActividad),
                    file.getContentType(), file.getSize());
        } catch (IOException ex) {
            throw new AlmacenamientoExcepcion("No se logró almacenar el archivo: " + fileName, HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis());
        }
    }

    private String createURI(final String fileName, final int idActividad) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/actividad/archivo/" + idActividad + "/")
                .path(fileName)
                .toUriString();
    }

    @Override
    public List<String> obtenerURIsActividad(int idActividad) {
        Path actividadPath = this.fileStorageLocation.resolve(String.valueOf(idActividad));
        if(!Files.exists(actividadPath))
            return null;

        if (this.actividadServicio.consultarActividadPorId(idActividad) == null)
            throw new ActividadNoExiste("No existe actividad con el id " + idActividad, HttpStatus.NOT_FOUND, System.currentTimeMillis());

        File folder = new File(actividadPath.toString());
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        return Arrays.stream(listOfFiles)
                .map(File::getName)
                .map(fileName -> this.createURI(fileName, idActividad))
                .collect(Collectors.toList());
    }

    @Override
    public Resource cargarArchivoComoResource(String fileName, int idActividad) {
        try {
            Path filePath = this.fileStorageLocation.resolve(idActividad + "/" + fileName).normalize();
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

    @Override
    public void borrarArchivos(int idActividad) {
        Path actividadPath = this.fileStorageLocation.resolve(String.valueOf(idActividad));
        if(!Files.exists(actividadPath))
            return;

        if (this.actividadServicio.consultarActividadPorId(idActividad) == null)
            throw new ActividadNoExiste("No existe actividad con el id " + idActividad, HttpStatus.NOT_FOUND, System.currentTimeMillis());

        try {
            FileUtils.forceDelete(new File(actividadPath.toString()));
        } catch (IOException e) {
            throw new AlmacenamientoExcepcion("Error al borrar los archivos de la actividad " + idActividad, HttpStatus.INTERNAL_SERVER_ERROR, System.currentTimeMillis());
        }
    }

    @Override
    public List<ArchivoModelo> crearArchivoEntidades(List<String> urIs) {
        return urIs.stream()
                .map(this::crearArchivoEntidad)
                .collect(Collectors.toList());
    }

    private ArchivoModelo crearArchivoEntidad(String uri) {
        String[] split = uri.split("\\.");
        String extension = split[split.length - 1];
        return new ArchivoModelo(uri, this.esImagen(extension));
    }

    private boolean esImagen(String extension) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "tiff", "bmp"};
        return Arrays.asList(imageExtensions).contains(extension);
    }
}
