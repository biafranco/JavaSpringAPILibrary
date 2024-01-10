package com.librarylink.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TB_EMPRESTIMO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emprestimo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "LIVRO_ID")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "BIBLIOTECA_ID")
    private Biblioteca biblioteca;

    @Column(name = "DATA_INICIO")
    private Date data_inicio;

    @Column(name = "DATA_FIM")
    private Date data_fim;

    @Column(name = "SITUACAO", nullable = false)
    private String situacao;

}