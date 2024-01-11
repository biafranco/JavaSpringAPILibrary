package com.librarylink.api.service;

import com.librarylink.api.dto.BibliotecaDTO;
import com.librarylink.api.models.Biblioteca;
import com.librarylink.api.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BibliotecaService {
    @Autowired
    private BibliotecaRepository bibliotecaRep;

    public BibliotecaDTO convertEntityToDto(Biblioteca biblioteca) {
        BibliotecaDTO bibliotecaDTO = new BibliotecaDTO();
        bibliotecaDTO.setId(biblioteca.getId());
        bibliotecaDTO.setEndereco(biblioteca.getEndereco());
        bibliotecaDTO.setProprietario(biblioteca.getProprietario());
        bibliotecaDTO.setTelefone(biblioteca.getTelefone());
        return bibliotecaDTO;
    }

    public Biblioteca convertDtoToEntity(BibliotecaDTO bibliotecaDTO) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setId(bibliotecaDTO.getId());
        biblioteca.setEndereco(bibliotecaDTO.getEndereco());
        biblioteca.setProprietario(bibliotecaDTO.getProprietario());
        biblioteca.setTelefone(bibliotecaDTO.getTelefone());
        return biblioteca;
    }
}
