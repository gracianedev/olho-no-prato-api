package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail (String email);
}
