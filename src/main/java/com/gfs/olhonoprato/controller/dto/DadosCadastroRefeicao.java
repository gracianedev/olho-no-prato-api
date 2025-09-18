package com.gfs.olhonoprato.controller.dto;

import com.gfs.olhonoprato.model.TipoRefeicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroRefeicao (
    @NotBlank
    String urlFoto,

    @NotNull
    TipoRefeicao tipoRefeicao,

    String descricao
){
}
