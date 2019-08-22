package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.TokenRecuperacionEntidad;
import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.TokenNoValidoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioNoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.TokensRecuperacionRepositorio;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenRecuperacionServicioImpl implements  TokenRecuperacionServicio {
    private TokensRecuperacionRepositorio tokenRepositorio;
    private UsuariosRepositorio usuariosRepositorio;
    private CorreosServicio correosServicio;
    private PasswordEncoder passwordEncoder;

    public TokenRecuperacionServicioImpl(TokensRecuperacionRepositorio tokenRepositorio, UsuariosRepositorio usuariosRepositorio, CorreosServicio correosServicio, PasswordEncoder passwordEncoder) {
        this.tokenRepositorio = tokenRepositorio;
        this.usuariosRepositorio = usuariosRepositorio;
        this.correosServicio = correosServicio;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String generarToken(final String correo) {
        UsuarioEntidad usuario = usuariosRepositorio.findById(correo)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoExcepcion(
                                "El correo dado no corresponde a ningún usuario",
                                HttpStatus.NOT_FOUND,
                                System.currentTimeMillis()));

        String tokenRecuperacion = RandomStringUtils.random(5, true, true);
        TokenRecuperacionEntidad token = TokenRecuperacionEntidad.builder()
                .usuario(usuario)
                .token(passwordEncoder.encode(tokenRecuperacion))
                .build();

        tokenRepositorio.findById(usuario.getCorreo())
                .ifPresent(t -> tokenRepositorio.delete(t));
        tokenRepositorio.delete(TokenRecuperacionEntidad.builder()
                .usuario(usuario)
                .build());
        tokenRepositorio.save(token);

        correosServicio.enviarCorreoGenerico(correo,
                "Código para recuperar contraseña (Tropicalización de la tecnología)",
                "Su código es " + tokenRecuperacion);

        return tokenRecuperacion;
    }

    @Override
    public void validarYBorrarToken(final String correo, final String token) {
        usuariosRepositorio.findById(correo)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoExcepcion(
                                "El correo dado no corresponde a ningún usuario",
                                HttpStatus.NOT_FOUND,
                                System.currentTimeMillis()));

        tokenRepositorio.findById(correo)
                .filter(t -> passwordEncoder.matches(token, t.getToken()))
                .map(t -> {
                    tokenRepositorio.delete(t);
                    return t;
                })
                .orElseThrow( () ->
                        new TokenNoValidoExcepcion("El token de recuperación no concuerda o no existe solicitud de cambio de contrasenna",
                HttpStatus.CONFLICT,
                System.currentTimeMillis()));

    }
}
