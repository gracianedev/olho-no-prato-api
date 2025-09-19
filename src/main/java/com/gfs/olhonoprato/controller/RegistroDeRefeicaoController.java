package com.gfs.olhonoprato.controller;

import com.gfs.olhonoprato.controller.dto.DadosCadastroPeso;
import com.gfs.olhonoprato.controller.dto.DadosCadastroRefeicao;
import com.gfs.olhonoprato.controller.dto.DadosPeso;
import com.gfs.olhonoprato.controller.dto.DadosRefeicao;
import com.gfs.olhonoprato.model.TipoRefeicao;
import com.gfs.olhonoprato.service.RegistroDeRefeicaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
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
    public ResponseEntity<Page<DadosRefeicao>> listarTodos(@PageableDefault(size = 10, sort = {"dataRegistro"}) Pageable pageable) {
        var registrosRefeicao = refeicaoService.listarTodos(pageable);
        return ResponseEntity.ok(registrosRefeicao);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<DadosRefeicao>> buscar(
            @RequestParam TipoRefeicao tipo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            Pageable pageable
    ) {
        var paginaDeDtos = refeicaoService.buscarPorFiltros(tipo, dataInicial, dataFinal, pageable);
        return ResponseEntity.ok(paginaDeDtos);
    }
}
