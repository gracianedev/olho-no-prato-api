package com.gfs.olhonoprato.service;

import com.gfs.olhonoprato.controller.dto.DadosCadastroPeso;
import com.gfs.olhonoprato.controller.dto.DadosPeso;
import com.gfs.olhonoprato.controller.dto.DadosRefeicao;
import com.gfs.olhonoprato.model.RegistroDePeso;
import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.RegistroDePesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class RegistroDePesoService {

    @Autowired
    private RegistroDePesoRepository registroDePesoRepository;

    @Transactional
    public DadosPeso cadastrarDadosPeso(DadosCadastroPeso novoCadastroPeso) {

        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var registroDePeso = new RegistroDePeso();
        registroDePeso.setUsuario(usuario);
        registroDePeso.setPeso(novoCadastroPeso.peso());
        registroDePeso.setDataRegistro(LocalDateTime.now());
        var registroSalvo = registroDePesoRepository.save(registroDePeso);

        return new DadosPeso(registroSalvo);
    }

    public Page<DadosPeso> listarTodos(Pageable pageable) {
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var paginaDeEntidades = registroDePesoRepository.findByUsuario(usuario, pageable);

        return paginaDeEntidades.map(DadosPeso::new);

    }


    public Page<DadosPeso> buscarPorFiltros(
            LocalDate dataInicial,
            LocalDate dataFinal,
            Pageable pageable
    ) {
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Conversão de LocalDate para LocalDateTime para buscar no dia inteiro
        var inicioDoPeriodo = dataInicial.atStartOfDay(); // Ex: 2025-09-18 -> 2025-09-18T00:00:00
        var fimDoPeriodo = dataFinal.atTime(LocalTime.MAX); // Ex: 2025-09-18 -> 2025-09-18T23:59:59.999...

        return registroDePesoRepository.findByUsuarioAndDataRegistroBetween(
                usuario, inicioDoPeriodo, fimDoPeriodo, pageable
        ).map(DadosPeso::new);
    }

}
