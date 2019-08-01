package com.tropicalizacion.tropicalizacionbackend.repositorios;

import com.tropicalizacion.tropicalizacionbackend.entidades.bd.TokenRecuperacionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRecuperacionRepositorio extends JpaRepository<TokenRecuperacionEntidad, String> {
}
