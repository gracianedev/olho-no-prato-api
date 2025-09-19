package com.gfs.olhonoprato.repository;

import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import com.gfs.olhonoprato.model.TipoRefeicao;
import com.gfs.olhonoprato.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

/* Ao alterar para PagingAndSortingRepository ficou indisponível a opção save,
por isso foi incluído CrudRepository como solução temporária
 */

@Repository
public interface RegistroDeRefeicaoRepository extends PagingAndSortingRepository<RegistroDeRefeicao, Long>, CrudRepository<RegistroDeRefeicao, Long> {

     Page<RegistroDeRefeicao> findByUsuario(Usuario usuario, Pageable pageable);

    Page<RegistroDeRefeicao> findByUsuarioAndTipoRefeicaoAndDataRegistroBetween(
            Usuario usuario,
            TipoRefeicao tipo,
            LocalDateTime dataInicial,
            LocalDateTime dataFinal,
            Pageable pageable
    );
}
