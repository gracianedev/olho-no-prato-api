package com.gfs.olhonoprato.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usuarioId;

    @NotBlank(message = "O campo nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
    @Email(message = "O e-mail informado não é válido.")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Informe a senha.")
    @Column(nullable = false)
    @Size(min=6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

// Métodos do UserDetail
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    // Por enquanto, vamos retornar uma role "USER" padrão para todos.
    // No futuro, poderíamos ter roles como "ADMIN".
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
}

    @Override
    public String getPassword() {
        return this.senha; // O Spring Security usará este método para pegar a senha (já hasheada)
    }

    @Override
    public String getUsername() {
        return this.email; // Usaremos o email como username para o Spring Security
    }

    // Por enquanto, podemos deixar os métodos abaixo como 'true'
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }


}
