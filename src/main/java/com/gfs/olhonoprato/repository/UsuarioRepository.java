package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
