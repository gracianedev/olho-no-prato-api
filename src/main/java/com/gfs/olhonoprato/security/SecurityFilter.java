package com.gfs.olhonoprato.security;

import com.gfs.olhonoprato.repository.UsuarioRepository;
import com.gfs.olhonoprato.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {

            // Valida o token e extrai o subject (email)
            var subject = tokenService.getSecret(tokenJWT);
            // Recupera os dados do usuário
            var usuario = usuarioRepository.findByEmail(subject).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            // Cria um objeto de autenticação para o Spring
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            // Define o usuário como autenticado para a segurança do Spring
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Independentemente de ter autenticado ou não, continua o fluxo da requisição
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        // Busca pelo cabeçalho "Authorization"
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // O token sempre vem depois do prefixo "Bearer ", então o removemos.
            return authorizationHeader.replace("Bearer ", "");
        }

        // Se não encontrou o cabeçalho, retorna nulo.
        return null;
    }
}
