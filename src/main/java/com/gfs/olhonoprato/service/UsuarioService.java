package com.gfs.olhonoprato.service;

import com.gfs.olhonoprato.exception.EmailJaCadastradoException;
import com.gfs.olhonoprato.model.Usuario;
import com.gfs.olhonoprato.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario cadastrar (Usuario novoUsuario){
       if (usuarioRepository.findByEmail(novoUsuario.getEmail()).isPresent())
            {
            throw new EmailJaCadastradoException("O email informado já está em uso.");
            }

        String senhaOriginal = novoUsuario.getSenha();
        String senhaHash = passwordEncoder.encode(senhaOriginal);
        novoUsuario.setSenha(senhaHash);
        return usuarioRepository.save(novoUsuario);
    }
}
