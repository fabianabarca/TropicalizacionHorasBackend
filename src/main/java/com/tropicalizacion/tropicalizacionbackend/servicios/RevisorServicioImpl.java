package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.RevisorEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioNoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioYaExisteExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.RevisoresRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Author: jdvar
 * Date: 10/23/2019
 * Time: 12:37 PM
 **/
@Service
public class RevisorServicioImpl implements RevisorServicio {
    private RevisoresRepositorio revisoresRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private CorreosServicio correosServicio;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RevisorServicioImpl(RevisoresRepositorio revisoresRepositorio, UsuariosRepositorio usuariosRepositorio, CorreosServicio correosServicio, PasswordEncoder passwordEncoder) {
        this.revisoresRepositorio = revisoresRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.correosServicio = correosServicio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void agregarRevisor(RevisorEntidad revisorEntidad) {
        if (this.revisoresRepositorio.findById(revisorEntidad.getUsuario().getCorreo()).isPresent())
            throw new UsuarioYaExisteExcepcion("El correo " + revisorEntidad.getCorreoUsuario() + " ya está tomado",
                    HttpStatus.CONFLICT, System.currentTimeMillis());

        // Codificar la contraseña dada, por ahora todas son "contrasenna", después el usuario tendrá que cambiarla
        String contrasennaNueva = RandomStringUtils.random(10, true, true);
        String contasennaEncriptada = passwordEncoder.encode(contrasennaNueva);
        revisorEntidad.getUsuario().setContrasenna(contasennaEncriptada);
        revisorEntidad.getUsuario().setActivado(false);
        revisorEntidad.getUsuario().setBorrado(false);

        UsuarioEntidad usuarioGuardado = usuariosRepositorio.save(revisorEntidad.getUsuario());
        revisorEntidad.setUsuario(usuarioGuardado);
        revisoresRepositorio.save(revisorEntidad);

        correosServicio.enviarContrasennaNueva(contrasennaNueva, revisorEntidad.getUsuario().getCorreo());
    }

    @Override
    public void borrarRevisor(String correoRevisor) {
        RevisorEntidad revisorEntidad = this.consultarRevisorPorId(correoRevisor);
        revisorEntidad.setBorrado(true);
        revisorEntidad.getUsuario().setBorrado(true);

        this.revisoresRepositorio.save(revisorEntidad);
    }

    @Override
    public void modificarRevisor(RevisorEntidad nuevoRevisor) {
        RevisorEntidad revisorEntidad = this.consultarRevisorPorId(nuevoRevisor.getUsuario().getCorreo());

        revisorEntidad.getUsuario().setApellidos(nuevoRevisor.getUsuario().getApellidos());
        revisorEntidad.getUsuario().setNombre(nuevoRevisor.getUsuario().getNombre());
        revisorEntidad.getUsuario().setTelefono(nuevoRevisor.getUsuario().getTelefono());
        revisorEntidad.setEsCoordinador(nuevoRevisor.isEsCoordinador());

        this.revisoresRepositorio.save(revisorEntidad);
    }

    @Override
    public Page<RevisorEntidad> getRevisores(Integer pagina, Integer limite) {
        return this.revisoresRepositorio.findAll(PageRequest.of(pagina, limite));
    }

    @Override
    public RevisorEntidad consultarRevisorPorId(String id) {
        return this.revisoresRepositorio.findById(id).orElseThrow(() ->
                new UsuarioNoEncontradoExcepcion("El usuario con correo " + id + " no existe", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
    }
}
