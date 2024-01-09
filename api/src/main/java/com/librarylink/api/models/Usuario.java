package com.librarylink.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.librarylink.api.common.UserCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "TB_USUARIO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CATEGORIA", nullable = false)
    private String categoria;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "TELEFONE", nullable = false)
    private String telefone;

}