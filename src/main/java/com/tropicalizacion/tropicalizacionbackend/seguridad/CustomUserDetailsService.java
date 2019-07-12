package com.tropicalizacion.tropicalizacionbackend.seguridad;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.UsuarioEntidad;
import com.tropicalizacion.tropicalizacionbackend.excepciones.UsuarioNoEncontradoExcepcion;
import com.tropicalizacion.tropicalizacionbackend.repositorios.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Implementación custom de UserDetailsService para devolver instancias de nuestro UserDetails custom
 * @author      Jose David Vargas Artavia
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UsuariosRepositorio users;

    @Autowired
    public CustomUserDetailsService(UsuariosRepositorio users) {
        this.users = users;
    }

    /**
     * Método que se sobrecarga para devolver instancias de CustomUserDetails
     *
     * @param username correo del usuario
     * @return retorna el UserDetails del usuario que se dió por parámetro
     * @throws UsernameNotFoundException en caso de que el correo no corresponda a ningún usuario
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntidad> usuario = users.findById(username);
        if(usuario.isPresent()){
            String rol = usuario.get().getEstudiante() != null ?
                    "Estudiante"
                    : (usuario.get().getRevisor().isEsCoordinador() ?
                        "Coordinador"
                        :
                        "Revisor");

            return new CustomUserDetails(usuario.get(), rol);
        }
        else{
            throw new UsuarioNoEncontradoExcepcion("Username: " + username + " not found", HttpStatus.NOT_FOUND, System.currentTimeMillis());
        }
    }
}