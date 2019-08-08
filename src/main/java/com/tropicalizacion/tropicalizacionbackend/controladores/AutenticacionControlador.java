package com.tropicalizacion.tropicalizacionbackend.controladores;

import com.tropicalizacion.tropicalizacionbackend.entidades.AutenticacionUsuario;
import com.tropicalizacion.tropicalizacionbackend.entidades.CustomResponse;
import com.tropicalizacion.tropicalizacionbackend.entidades.RecuperarContrasenna;
import com.tropicalizacion.tropicalizacionbackend.excepciones.JwtInvalidoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.excepciones.MalasCredencialesExcepcion;
import com.tropicalizacion.tropicalizacionbackend.servicios.AutenticacionServicio;
import com.tropicalizacion.tropicalizacionbackend.servicios.TokenRecuperacionServicio;
import com.tropicalizacion.tropicalizacionbackend.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.security.Principal;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping(value = "/autenticar")
public class AutenticacionControlador {
     private final AutenticacionServicio autenticacionServicio;
     private final TokenRecuperacionServicio tokenRecuperacionServicio;
     private final UsuarioServicio usuarioServicio;

    @Autowired
    public AutenticacionControlador(AutenticacionServicio autenticacionServicio, TokenRecuperacionServicio tokenRecuperacionServicio, UsuarioServicio usuarioServicio) {
        this.autenticacionServicio = autenticacionServicio;
        this.tokenRecuperacionServicio = tokenRecuperacionServicio;
        this.usuarioServicio = usuarioServicio;
    }

    /**
     * Endpoint para hacer sign in
     *
     * @param infoUsuario Modelo del request de autenticación. Espera los atributos username y password
     * @return el JWT en caso de un sign in exitoso
     * @throws MethodArgumentNotValidException Si la instancia de AutenticacionUsuario no se validó correctamente
     */
    @PostMapping("/sign-in")
    public ResponseEntity<CustomResponse> signIn(@Valid @RequestBody AutenticacionUsuario infoUsuario) throws MethodArgumentNotValidException {
        try {
            CustomResponse response = new CustomResponse((Object) autenticacionServicio.autenticarUsuario(infoUsuario.getCorreoUsuario(), infoUsuario.getContrasenna()));
            return ok(response);
        } catch (MalasCredencialesExcepcion e) {
            throw new MalasCredencialesExcepcion("Correo o contraseña inválido", HttpStatus.UNAUTHORIZED, System.currentTimeMillis());
        }
    }

    /**
     * Valida si el token dado es válido
     *
     * @param token token a validar
     * @return retornar ok si es válido o Unauthorized si no
     */
    @GetMapping("/validar")
    public ResponseEntity<CustomResponse> validarToken(@RequestParam String token) {
        boolean validez = this.autenticacionServicio.esTokenValido(token);
        if (validez)
            return ok(new CustomResponse());
        else
            throw new JwtInvalidoExcepcion("Jwt vencido", HttpStatus.UNAUTHORIZED, System.currentTimeMillis());
    }

    /**
     * Endpoint para comenzar el proceso de recuperar la contraseña. Genera un token de
     * recuperación que es guardado en la bd y enviado por correo
     *
     * @param correo Correo del usuario que desea recuperar su contraseña
     * @return retorna ok si el proceso se realiza correctamenta
     */
    @PostMapping("/recuperar/{correo}")
    public ResponseEntity<CustomResponse> recuperarContrasenna(@PathVariable @Email String correo) {
        String token = tokenRecuperacionServicio.generarToken(correo);
        return ok(new CustomResponse("Se generó correctamente el token de recuperación " + token, null, null));
    }

    /**
     * Finaliza el proceso de recuperar contraseña.
     * Valida que el token dado sea el mismo que el almacenado y lo borra si lo son. Luego realiza el cambio de la contraseña
     *
     * @param info Contiene la información del cambio de la contraseña
     * @return ok si el proceso se realiza correctamente
     */
    @PutMapping("/recuperar")
    ResponseEntity<CustomResponse> cambiarContrasennaRecuperacion(@Valid @RequestBody RecuperarContrasenna info) {
        tokenRecuperacionServicio.validarYBorrarToken(info.getCorreo(), info.getTokenRecuperacion());
        usuarioServicio.cambiarContrasenna(info.getCorreo(), info.getContrasennaNueva());
        return ok(new CustomResponse("El cambio de contraseña fue exitoso", null, null));
    }

    /**
     * Permite cambiar la contraseña del usuario en caso de que ya estuviera loggeado
     *
     * @param usuario usuario loggeado
     * @param contrasennaNueva contraseña nueva que desea el usuario
     * @return ok si sale bien el proceso
     */
    @PutMapping("/cambiar-contrasenna")
    @PreAuthorize("isAuthenticated()")
    ResponseEntity<CustomResponse> cambiarContrasenna(@AuthenticationPrincipal Principal usuario, @RequestBody String contrasennaNueva) {
        usuarioServicio.cambiarContrasenna(usuario.getName(), contrasennaNueva);
        return ok(new CustomResponse("Cambio exitoso", null, null));
    }
}
