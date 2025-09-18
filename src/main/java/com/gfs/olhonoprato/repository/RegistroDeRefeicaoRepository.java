package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroDeRefeicaoRepository extends JpaRepository <RegistroDeRefeicao, Long> {
}
