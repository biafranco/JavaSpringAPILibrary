package com.librarylink.api.dto;

import com.librarylink.api.models.Biblioteca;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LivroDTO {

    private Long id;
    private Long bibliotecaId;
    private String titulo;
    private String autor;
    private String categoria;
    private Date data_lancamento;
    private Date data_compra;
    private int numero_pagina;

}
