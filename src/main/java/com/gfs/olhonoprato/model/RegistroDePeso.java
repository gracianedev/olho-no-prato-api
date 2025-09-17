package com.gfs.olhonoprato.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="registros_peso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "registroId")
public class RegistroDePeso {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registroId;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "usuarioId" , nullable = false)
    private Usuario usuario;


}
