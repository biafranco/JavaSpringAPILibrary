package com.librarylink.api.service.schedule;

import com.librarylink.api.dto.LivroDTO;
import com.librarylink.api.models.Livro;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LivroService {
    public LivroDTO convertEntityToDto(Livro livro);
    public Livro convertDtoToEntity(LivroDTO livroDTO);
    public void saveAll(List<LivroDTO> livros);
    public List<LivroDTO> findAll();
    public Optional<LivroDTO> findById(Long id);
    public LivroDTO updateLivro(Long id, LivroDTO livroDTO);
    void deleteById(Long id);
}
