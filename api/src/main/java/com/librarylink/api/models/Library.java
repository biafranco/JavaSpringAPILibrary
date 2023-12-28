package com.librarylink.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Entity
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codigo;

    @Getter
    @Setter
    @NotEmpty
    private String endereco;

    @Getter
    @Setter
    @NotEmpty
    private String proprietario;

    @Getter
    @Setter
    @OneToMany(mappedBy = "library")
    private List<Book> books;


    public Library(int codigo, String endereco, String proprietario, List<Book> books) {
        this.codigo = codigo;
        this.endereco = endereco;
        this.proprietario = proprietario;
        this.books = books;
    }

}
