package com.librarylink.api.models;

import com.librarylink.api.common.UserCategory;
import jakarta.persistence.*;
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
    private int codigo;

    @Setter
    @Getter
    private UserCategory categoria;

    @Setter
    @Getter
    private String nome;

    @Setter
    @Getter
    private String endereco;

    @Setter
    @Getter
    private String telefone;

    public Usuario(int codigo, UserCategory categoria, String nome, String endereco, String telefone) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String toString() {
        return "Usuario [id=" + id + ", codigo=" + codigo + ", categoria=" + categoria + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + "]";
    }
}
