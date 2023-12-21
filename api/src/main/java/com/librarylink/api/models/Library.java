package com.librarylink.api.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@Table(name = "LIBRARY")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Getter
    private int codigo;

    @Setter
    @Getter
    private String endereco;

    @Setter
    @Getter
    private String proprietario;

    @Setter
    @Getter
    private String telefone;

    public Library(int codigo, String endereco, String proprietario, String telefone) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.telefone = telefone;
    }

    public String toString() {
        return "Library [id=" + id + ", codigo=" + codigo + ", endereco=" + endereco + ", proprietario=" + proprietario + ", telefone=" + telefone + "]";
    }
}
