package com.librarylink.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "TB_LIVRO")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Livro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BIBLIOTECA_ID")
    private Biblioteca biblioteca;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @Column(name = "AUTOR", nullable = false)
    private String autor;

    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Column(name = "DATA_LANCAMENTO")
    private Date data_lancamento;

    @Column(name = "DATA_COMPRA")
    private Date data_compra;

    @Column(name = "NUMERO_PAGINA")
    private int numero_pagina;

}
