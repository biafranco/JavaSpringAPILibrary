package com.librarylink.api.service.impl;

import com.librarylink.api.dto.BibliotecaDTO;
import com.librarylink.api.models.Biblioteca;
import com.librarylink.api.models.Livro;
import com.librarylink.api.repository.BibliotecaRepository;
import com.librarylink.api.service.schedule.BibliotecaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BibliotecaServiceImpl implements BibliotecaService {
    @Autowired
    private BibliotecaRepository bibliotecaRep;

    @Override
    public BibliotecaDTO convertEntityToDto(Biblioteca biblioteca) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(biblioteca, BibliotecaDTO.class);
    }

    @Override
    public Biblioteca convertDtoToEntity(BibliotecaDTO bibliotecaDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(bibliotecaDTO, Biblioteca.class);
    }

    @Override
    public List<BibliotecaDTO> saveAll(List<BibliotecaDTO> bibliotecas) {
        List<Biblioteca> bibliotecaEntity = bibliotecas.stream()
                .map(this::convertDtoToEntity)
                .toList();

        bibliotecaRep.saveAll(bibliotecaEntity);
        return bibliotecaEntity.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        bibliotecaRep.deleteById(id);
    }

    @Override
    public Optional<BibliotecaDTO> findById(Long id) {
        Optional<Biblioteca> bibliotecaEntity = bibliotecaRep.findById(id);
        return bibliotecaEntity.map(this::convertEntityToDto);
    }

    @Override
    public BibliotecaDTO editBiblioteca(Long id, BibliotecaDTO bibliotecaDTO) {
        Optional<Biblioteca> bibliotecaEntity = bibliotecaRep.findById(id);
        Biblioteca biblioteca = bibliotecaEntity.get();
        biblioteca.setId(bibliotecaDTO.getId());
        biblioteca.setEndereco(bibliotecaDTO.getEndereco());
        biblioteca.setProprietario(bibliotecaDTO.getProprietario());
        biblioteca.setTelefone(bibliotecaDTO.getTelefone());
        bibliotecaRep.save(biblioteca);

        return convertEntityToDto(biblioteca);
    }

    @Override
    public List<BibliotecaDTO> findAll() {

        return bibliotecaRep.findAll().stream()
                .map(this::convertEntityToDto)
                .toList();
    }
}
