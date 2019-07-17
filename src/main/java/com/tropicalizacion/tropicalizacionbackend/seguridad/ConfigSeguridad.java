package com.tropicalizacion.tropicalizacionbackend.seguridad;

import com.tropicalizacion.tropicalizacionbackend.seguridad.jwt.JwtConfigurer;
import com.tropicalizacion.tropicalizacionbackend.seguridad.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfigSeguridad extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public ConfigSeguridad(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Crea el bean de Authentication Manager
     *
     * @return la instancia de AuthenticationManager
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configura el bean de PasswordEncoder que se utilizará para encriptar.
     *
     * @return la instancia del PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *  Método principal de configuración donde se incluyen las reglas de autenticación y acceso a los endpoints
     *
     * @param http instancia de seguridad
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                // El has authority se usa para definir cuáles permisos permiten acceder a ese endpoint
                .anyRequest().permitAll()
                .and()
                // Se aplica el filtro de los JWT
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
