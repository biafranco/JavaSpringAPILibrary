package com.librarylink.api.service.impl;

import com.librarylink.api.dto.LivroDTO;
import com.librarylink.api.models.Livro;
import com.librarylink.api.repository.BibliotecaRepository;
import com.librarylink.api.repository.LivroRepository;
import com.librarylink.api.service.schedule.LivroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class LivroServiceImpl implements LivroService {

    @Autowired
    public LivroRepository livroRep;
    @Autowired
    static BibliotecaRepository bibliotecaRep;

    public LivroDTO convertEntityToDto(Livro livro) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(livro, LivroDTO.class);
    }

    public Livro convertDtoToEntity(LivroDTO livroDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(livroDTO, Livro.class);
    }

    public void saveAll(List<LivroDTO> livros) {
        List<Livro> livrosEntity = livros.stream()
                .map(this::convertDtoToEntity)
                .toList();

        livroRep.saveAll(livrosEntity);
    }

    public List<LivroDTO> findAll() {
        List<Livro> livrosEntity = livroRep.findAll();

        return livrosEntity.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public Optional<LivroDTO> findById(Long id) {
        Optional<Livro> livroEntity = livroRep.findById(id);

        return livroEntity.map(this::convertEntityToDto);
    }

    public LivroDTO updateLivro(Long id, LivroDTO livroDTO) {
        Livro livro = livroRep.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrada com o ID: " + id));
        livro.setTitulo(livroDTO.getTitulo());
        livro.setAutor(livroDTO.getAutor());
        livro.setCategoria(livroDTO.getCategoria());
        livro.setData_lancamento(livroDTO.getData_lancamento());
        livro.setData_compra(livroDTO.getData_compra());
        livro.setNumero_pagina(livroDTO.getNumero_pagina());
        livro.setBiblioteca(bibliotecaRep.findById(livroDTO.getBibliotecaId()).get());
        livroRep.save(livro);
        return convertEntityToDto(livro);
    }

    @Override
    public void deleteById(Long id) {
        livroRep.deleteById(id);
    }
}

