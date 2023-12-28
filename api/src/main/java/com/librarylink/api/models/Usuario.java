package com.librarylink.api.models;

import com.librarylink.api.common.UserCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotEmpty
    private int codigo;

    @Getter
    @Setter
    private UserCategory userType;

    @Setter
    @Getter
    @NotEmpty
    private String nome;

    @Setter
    @Getter
    private String endereco;

    @Setter
    @Getter
    @NotEmpty
    private String telefone;

    public Usuario(int codigo, UserCategory userType, String nome, String endereco, String telefone) {
        this.codigo = codigo;
        this.userType = userType;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

}
