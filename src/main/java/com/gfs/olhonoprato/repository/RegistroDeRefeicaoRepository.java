package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import com.gfs.olhonoprato.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroDeRefeicaoRepository extends JpaRepository <RegistroDeRefeicao, Long> {

     List<RegistroDeRefeicao> findByUsuario(Usuario usuario);
}
