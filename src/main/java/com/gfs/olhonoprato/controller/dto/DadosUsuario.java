package com.gfs.olhonoprato.controller.dto;

import com.gfs.olhonoprato.model.Usuario;

public record DadosUsuario(
    Long id,
    String nome,
    String email
    ){

    public DadosUsuario (Usuario usuario) {
        this(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
