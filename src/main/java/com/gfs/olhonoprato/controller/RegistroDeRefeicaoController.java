package com.gfs.olhonoprato.controller;

import com.gfs.olhonoprato.controller.dto.DadosCadastroPeso;
import com.gfs.olhonoprato.controller.dto.DadosCadastroRefeicao;
import com.gfs.olhonoprato.controller.dto.DadosPeso;
import com.gfs.olhonoprato.controller.dto.DadosRefeicao;
import com.gfs.olhonoprato.service.RegistroDeRefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("refeicoes")
public class RegistroDeRefeicaoController {

    @Autowired
    private RegistroDeRefeicaoService refeicaoService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosRefeicao> cadastrarRefeicao (@RequestBody @Valid DadosCadastroRefeicao dadosCadastroRefeicao, UriComponentsBuilder uriBuilder ) {
        var dto = refeicaoService.cadastrarRefeicao(dadosCadastroRefeicao);
        var uri = uriBuilder.path("/refeicoes/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<List<DadosRefeicao>> listarTodos() {
        var registrosRefeicao = refeicaoService.listarTodos();
        return ResponseEntity.ok(registrosRefeicao);
    }
}
