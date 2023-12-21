package com.librarylink.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private int codigo;

    @Setter
    @Getter
    private String categoria;

    @Setter
    @Getter
    @Column(name = "autor")
    private String autor;

    @Setter
    @Getter
    private Date dataLancamento;

    @Setter
    @Getter
    private int numeroPaginas;

    @Setter
    @Getter
    private Date dataCompra;

    public Book(int codigo, String categoria, String name, String autor) {
        this.codigo = codigo;
        this.categoria = categoria;
        this.name = name;
        this.autor = autor;
    }

    public String toString() {
        return "Tutorial [id=" + id + ", cod=" + codigo + ", autor=" + autor + ", name=" + name+"]";
    }
}
