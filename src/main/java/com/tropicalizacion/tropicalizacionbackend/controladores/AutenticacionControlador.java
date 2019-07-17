package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.AutenticacionUsuario;
import com.tropicalizacion.tropicalizacionbackend.excepciones.MalasCredencialesExcepcion;
import com.tropicalizacion.tropicalizacionbackend.seguridad.jwt.JwtTokenProvider;
import com.tropicalizacion.tropicalizacionbackend.servicios.AutenticacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping(value = "/autenticar")
public class AutenticacionControlador {
     private final AutenticacionServicio autenticacionServicio;

    @Autowired
    public AutenticacionControlador(AutenticacionServicio autenticacionServicio) {
        this.autenticacionServicio = autenticacionServicio;
    }

    /**
     * Endpoint para hacer sign in
     *
     * @param infoUsuario Modelo del request de autenticación. Espera los atributos username y password
     * @return el JWT en caso de un sign in exitoso
     * @throws MethodArgumentNotValidException Si la instancia de AutenticacionUsuario no se validó correctamente
     */
    @PostMapping("/sign-in")
    public ResponseEntity signIn(@Valid @RequestBody AutenticacionUsuario infoUsuario) throws MethodArgumentNotValidException {
        try {
            return ok(autenticacionServicio.autenticarUsuario(infoUsuario.getCorreoUsuario(), infoUsuario.getContrasenna()));
        } catch (MalasCredencialesExcepcion e) {
            throw new MalasCredencialesExcepcion("Correo o contraseña inválido", HttpStatus.UNAUTHORIZED, System.currentTimeMillis());
        }
    }
}
