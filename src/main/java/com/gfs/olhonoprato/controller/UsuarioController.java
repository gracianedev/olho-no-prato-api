package com.gfs.olhonoprato.controller;

import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Responde a requisições HTTP POST para "/usuarios"
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario cadastrar (@RequestBody @Valid Usuario novoUsuario){
        return usuarioRepository.save(novoUsuario);

        // O @RequestBody converte o JSON vindo do cliente para um objeto Usuario
        // O @Valid aciona as validações que definimos na entidade (ex: @NotBlank, @Email)
    }
}
