package com.librarylink.api.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "TB_BIBLIOTECA")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Biblioteca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ENDERECO", nullable = false)
    private String endereco;

    @Column(name = "PROPRIETARIO", nullable = false)
    private String proprietario;

    @Column(name = "TELEFONE")
    private String telefone;

}