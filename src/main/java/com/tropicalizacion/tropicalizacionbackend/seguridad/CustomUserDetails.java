package com.tropicalizacion.tropicalizacionbackend.seguridad;

import com.tropicalizacion.tropicalizacionbackend.entidades.UsuarioEntidad;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación custom de UserDetails para introducir la lógica especial de negocio de los grupos y permisos
 * @author      Jose David Vargas Artavia
 */
public class CustomUserDetails implements UserDetails {
    private UsuarioEntidad user;
    private Collection<SimpleGrantedAuthority> permisos;

    public CustomUserDetails(UsuarioEntidad user, String rol) {
        this.user = user;
        this.permisos = Arrays.stream(new String[]{rol})
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return this.permisos;
    }

    @Override
    public String getPassword() {
        return user.getContrasenna();
    }

    @Override
    public String getUsername() {
        return user.getCorreo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}