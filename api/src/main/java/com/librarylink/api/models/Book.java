package com.librarylink.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("tittle")
    private String tittle;

    @JsonProperty("codigo")
    private int codigo;

    @JsonProperty("categoria")
    private String categoria;

    @JsonProperty("autor")
    private String autor;

    @JsonProperty("library")
    @JoinColumn(name = "library_id")
    @ManyToOne
    private Library library;
}
