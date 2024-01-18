package com.librarylink.api.service.schedule;

import com.librarylink.api.dto.BibliotecaDTO;
import com.librarylink.api.models.Biblioteca;
import com.librarylink.api.models.Livro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BibliotecaService {
    public BibliotecaDTO convertEntityToDto(Biblioteca biblioteca);
    public Biblioteca convertDtoToEntity(BibliotecaDTO bibliotecaDTO);

    List<BibliotecaDTO> saveAll(List<BibliotecaDTO> bibliotecas);

    void deleteById(Long id);

    Optional<BibliotecaDTO> findById(Long id);

    BibliotecaDTO editBiblioteca(Long id, BibliotecaDTO bibliotecaDTO);

    List<BibliotecaDTO> findAll();
}
