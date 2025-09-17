package com.gfs.olhonoprato.controller.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record DadosCadastroPeso(
        @NotNull
        @Positive
        Double peso) {
}
