package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.RegistroDePeso;
import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import com.gfs.olhonoprato.model.TipoRefeicao;
import com.gfs.olhonoprato.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/* Ao alterar para PagingAndSortingRepository ficou indisponível a opção save,
por isso foi incluído CrudRepository como solução temporária
 */
@Repository
public interface RegistroDePesoRepository extends PagingAndSortingRepository<RegistroDePeso, Long>, CrudRepository<RegistroDePeso, Long> {
    Page<RegistroDePeso> findByUsuario (Usuario usuario, Pageable pageable);

    Page<RegistroDePeso> findByUsuarioAndDataRegistroBetween(
            Usuario usuario,
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            Pageable pageable
    );
}
