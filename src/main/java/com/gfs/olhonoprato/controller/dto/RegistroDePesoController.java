package com.gfs.olhonoprato.controller.dto;

import com.gfs.olhonoprato.model.RegistroDePeso;
import com.gfs.olhonoprato.service.RegistroDePesoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<List<DadosPeso>> listarTodos() {
        var registrosPeso = registroDePesoService.listarTodos();
        return ResponseEntity.ok(registrosPeso);
    }


}
