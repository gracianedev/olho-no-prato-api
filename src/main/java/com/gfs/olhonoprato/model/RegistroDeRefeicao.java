package com.gfs.olhonoprato.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registros_refeicao")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RegistroDeRefeicao {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String urlFoto;

    @Column
    private String descricaoPrato;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRefeicao tipoRefeicao;

    @Column
    private LocalDateTime dataRegistro;

    @ManyToOne
    @JoinColumn(name = "usuarioId" , nullable = false)
    private Usuario usuario;


}
