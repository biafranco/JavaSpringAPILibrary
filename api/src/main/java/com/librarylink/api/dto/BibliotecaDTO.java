package com.librarylink.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BibliotecaDTO implements Serializable {

    private Long id;
    private String endereco;
    private String proprietario;
    private String telefone;

}
