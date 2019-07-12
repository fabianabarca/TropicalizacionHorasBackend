package com.tropicalizacion.tropicalizacionbackend.servicios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.MalasCredencialesExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import com.tropicalizacion.tropicalizacionbackend.seguridad.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final AuthenticationManager authenticationManager;
    private final UsuariosRepositorio usuariosRepositorio;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AutenticacionServicioImpl(AuthenticationManager authenticationManager, UsuariosRepositorio usuariosRepositorio, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.usuariosRepositorio = usuariosRepositorio;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @Transactional
    public String autenticarUsuario(String correo, String contrasenna) throws MalasCredencialesExcepcion {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(correo, contrasenna));
            UsuarioEntidad usuarioEntidad = this.usuariosRepositorio.findById(correo)
                    .orElseThrow(() -> new MalasCredencialesExcepcion("Usuario " + correo + " no encontrado", HttpStatus.NOT_FOUND, System.currentTimeMillis()));
            String rol = usuarioEntidad.getEstudiante() != null ?
                    "Estudiante"
                    : (usuarioEntidad.getRevisor().isEsCoordinador() ?
                    "Coordinador"
                    :
                    "Revisor");

            return jwtTokenProvider.createToken(usuarioEntidad, rol);
        }
        catch(AuthenticationException e) {
            throw new MalasCredencialesExcepcion("", HttpStatus.UNAUTHORIZED, System.currentTimeMillis());
        }
    }
}
