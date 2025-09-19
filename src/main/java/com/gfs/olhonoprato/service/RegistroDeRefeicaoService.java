package com.gfs.olhonoprato.service;

import com.gfs.olhonoprato.controller.dto.DadosCadastroRefeicao;
import com.gfs.olhonoprato.controller.dto.DadosRefeicao;
import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import com.gfs.olhonoprato.model.TipoRefeicao;
import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.RegistroDeRefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class RegistroDeRefeicaoService {

    @Autowired
    private RegistroDeRefeicaoRepository refeicaoRepository;

    @Transactional
    public DadosRefeicao cadastrarRefeicao (DadosCadastroRefeicao dadosCadastroRefeicao){
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var registroDeRefeicao = new RegistroDeRefeicao();
        registroDeRefeicao.setUsuario(usuario);
        registroDeRefeicao.setDataRegistro(LocalDateTime.now());
        registroDeRefeicao.setTipoRefeicao(dadosCadastroRefeicao.tipoRefeicao());
        registroDeRefeicao.setUrlFoto(dadosCadastroRefeicao.urlFoto());
        registroDeRefeicao.setDescricaoPrato(dadosCadastroRefeicao.descricao());

        var registroSalvo = refeicaoRepository.save(registroDeRefeicao);

        return new DadosRefeicao(registroSalvo);
    }

    public Page<DadosRefeicao> listarTodos(Pageable pageable){
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var paginaDeEntidades = refeicaoRepository.findByUsuario(usuario, pageable);
        return paginaDeEntidades.map(DadosRefeicao::new);

    }

    public Page<DadosRefeicao> buscarPorFiltros(
            TipoRefeicao tipo,
            LocalDate dataInicial,
            LocalDate dataFinal,
            Pageable pageable
    ) {
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Conversão de LocalDate para LocalDateTime para buscar no dia inteiro
        var inicioDoPeriodo = dataInicial.atStartOfDay(); // Ex: 2025-09-18 -> 2025-09-18T00:00:00
        var fimDoPeriodo = dataFinal.atTime(LocalTime.MAX); // Ex: 2025-09-18 -> 2025-09-18T23:59:59.999...

        return refeicaoRepository.findByUsuarioAndTipoRefeicaoAndDataRegistroBetween(
                usuario, tipo, inicioDoPeriodo, fimDoPeriodo, pageable
        ).map(DadosRefeicao::new);
    }


}
