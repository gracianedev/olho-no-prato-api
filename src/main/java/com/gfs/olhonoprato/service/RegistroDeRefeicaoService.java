package com.gfs.olhonoprato.service;

import com.gfs.olhonoprato.controller.dto.DadosCadastroRefeicao;
import com.gfs.olhonoprato.controller.dto.DadosRefeicao;
import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.RegistroDeRefeicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    public List<DadosRefeicao> listarTodos(){
        return refeicaoRepository.findAll()
                .stream()
                .map(DadosRefeicao::new)
                .toList();

    }



}
