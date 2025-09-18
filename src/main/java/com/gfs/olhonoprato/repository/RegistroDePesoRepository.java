package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.RegistroDePeso;
import com.gfs.olhonoprato.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroDePesoRepository extends JpaRepository <RegistroDePeso, Long> {
    List<RegistroDePeso> findByUsuario (Usuario usuario);
}
