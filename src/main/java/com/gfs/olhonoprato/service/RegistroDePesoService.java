package com.gfs.olhonoprato.service;

import com.gfs.olhonoprato.controller.dto.DadosCadastroPeso;
import com.gfs.olhonoprato.controller.dto.DadosPeso;
import com.gfs.olhonoprato.model.RegistroDePeso;
import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.RegistroDePesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroDePesoService {

    @Autowired
    private RegistroDePesoRepository registroDePesoRepository;

    @Transactional
    public DadosPeso cadastrarDadosPeso (DadosCadastroPeso novoCadastroPeso) {

        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var registroDePeso = new RegistroDePeso();
        registroDePeso.setUsuario(usuario);
        registroDePeso.setPeso(novoCadastroPeso.peso());
        registroDePeso.setDataRegistro(LocalDateTime.now());
        var registroSalvo = registroDePesoRepository.save(registroDePeso);

        return new DadosPeso(registroSalvo);
    }

    public List<DadosPeso> listarTodos () {
        var usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return registroDePesoRepository.findByUsuario(usuario)
               .stream()
               .map(DadosPeso::new)
               .toList();
    }
}
