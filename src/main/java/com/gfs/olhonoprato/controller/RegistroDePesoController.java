package com.gfs.olhonoprato.controller;

import com.gfs.olhonoprato.controller.dto.DadosCadastroPeso;
import com.gfs.olhonoprato.controller.dto.DadosPeso;
import com.gfs.olhonoprato.controller.dto.DadosRefeicao;
import com.gfs.olhonoprato.model.RegistroDePeso;
import com.gfs.olhonoprato.model.TipoRefeicao;
import com.gfs.olhonoprato.service.RegistroDePesoService;
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
@RequestMapping ("/registros-peso")
public class RegistroDePesoController {

    @Autowired
   private RegistroDePesoService registroDePesoService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DadosPeso> cadastrarPeso (@RequestBody @Valid DadosCadastroPeso dadosPeso, UriComponentsBuilder uriBuilder ) {
        var dto = registroDePesoService.cadastrarDadosPeso(dadosPeso);
        var uri = uriBuilder.path("/registros-peso/{id}").buildAndExpand(dto.registroId()).toUri();
        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<Page<DadosPeso>> listarTodos(@PageableDefault(size = 10, sort = {"dataRegistro"}) Pageable pageable) {
        var registrosPeso = registroDePesoService.listarTodos(pageable);
        return ResponseEntity.ok(registrosPeso);
    }


    @GetMapping("/buscar")
    public ResponseEntity<Page<DadosPeso>> buscar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal,
            Pageable pageable
    ) {
        var paginaDeDtos = registroDePesoService.buscarPorFiltros(dataInicial, dataFinal, pageable);
        return ResponseEntity.ok(paginaDeDtos);
    }


}
