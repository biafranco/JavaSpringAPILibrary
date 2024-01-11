package com.librarylink.api.dto;

import com.librarylink.api.common.UserCategory;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioDTO implements Serializable {

    private Long id;
    private UserCategory categoria;
    private String nome;
    private String endereco;
    private String telefone;

}
