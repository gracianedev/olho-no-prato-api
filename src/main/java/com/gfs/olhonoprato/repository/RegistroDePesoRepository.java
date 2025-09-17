package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.RegistroDePeso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDePesoRepository extends JpaRepository <RegistroDePeso, Long> {
}
