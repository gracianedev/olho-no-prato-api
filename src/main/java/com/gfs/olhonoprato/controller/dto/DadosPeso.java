package com.gfs.olhonoprato.controller.dto;

import com.gfs.olhonoprato.model.RegistroDePeso;

import java.time.LocalDateTime;

public record DadosPeso(
    Long registroId,
    Double peso,
    LocalDateTime dataRegistro
) {

    public DadosPeso (RegistroDePeso registroDePeso) {
        this(registroDePeso.getRegistroId(),
                registroDePeso.getPeso(),
                registroDePeso.getDataRegistro());
    }


}

