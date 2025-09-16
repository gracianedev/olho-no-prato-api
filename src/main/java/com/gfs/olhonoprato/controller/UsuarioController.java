package com.gfs.olhonoprato.controller;

import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.UsuarioRepository;
import com.gfs.olhonoprato.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Responde a requisições HTTP POST para "/usuarios"
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar (@RequestBody @Valid Usuario novoUsuario){
        return usuarioService.cadastrar(novoUsuario);

        // O @RequestBody converte o JSON vindo do cliente para um objeto Usuario
        // O @Valid aciona as validações que definimos na entidade (ex: @NotBlank, @Email)
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        var usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
}
