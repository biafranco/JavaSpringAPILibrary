package com.librarylink.api.dto;

import com.librarylink.api.common.SituacaoEmprestimo;
import com.librarylink.api.models.Biblioteca;
import com.librarylink.api.models.Livro;
import com.librarylink.api.models.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmprestimoDTO {

    private Long id;
    private Long usuarioId;
    private Long livroId;
    private Long bibliotecaId;
    private Date data_inicio;
    private Date data_fim;
    private SituacaoEmprestimo situacao;
}
