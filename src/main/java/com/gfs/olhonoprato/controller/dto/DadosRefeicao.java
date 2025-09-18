package com.gfs.olhonoprato.controller.dto;

import com.gfs.olhonoprato.model.RegistroDeRefeicao;
import com.gfs.olhonoprato.model.TipoRefeicao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosRefeicao(
        Long id,
        String urlFoto,
        String descricao,
        TipoRefeicao tipo,
        LocalDateTime dataRegistro
) {
    public DadosRefeicao(RegistroDeRefeicao registro) {
        this(registro.getId(),
                registro.getUrlFoto(),
                registro.getDescricaoPrato(),
                registro.getTipoRefeicao(),
                registro.getDataRegistro());
    }
}