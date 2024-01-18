package com.librarylink.api.service.impl;

import com.librarylink.api.dto.EmprestimoDTO;
import com.librarylink.api.models.Emprestimo;
import com.librarylink.api.repository.BibliotecaRepository;
import com.librarylink.api.repository.EmprestimoRepository;
import com.librarylink.api.repository.LivroRepository;
import com.librarylink.api.repository.UsuarioRepository;
import com.librarylink.api.service.schedule.EmprestimoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class EmprestimoServiceImpl implements EmprestimoService {
    @Autowired
    EmprestimoRepository emprestimoRep;

    @Autowired
    BibliotecaRepository bibliotecaRep;

    @Autowired
    LivroRepository livroRep;

    @Autowired
    UsuarioRepository usuarioRep;

    @Override
    public EmprestimoDTO convertEntityToDto(Emprestimo emprestimo) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(emprestimo, EmprestimoDTO.class);
    }

    @Override
    public Emprestimo convertDtoToEntity(EmprestimoDTO emprestimoDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(emprestimoDTO, Emprestimo.class);
    }

    @Override
    public Optional<EmprestimoDTO> findById(Long id) {
        Optional<Emprestimo> emprestimoEntity = emprestimoRep.findById(id);
        return emprestimoEntity.map(this::convertEntityToDto);
    }

    @Override
    public List<EmprestimoDTO> findAll() {
        List<Emprestimo> emprestimosEntity = emprestimoRep.findAll();
        return emprestimosEntity.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        emprestimoRep.deleteById(id);
    }

    @Override
    public List<EmprestimoDTO> saveAll(List<EmprestimoDTO> emprestimos) {
        List<Emprestimo> emprestimosEntity = emprestimos.stream()
                .map(this::convertDtoToEntity)
                .toList();

        emprestimoRep.saveAll(emprestimosEntity);

        return emprestimosEntity.stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public EmprestimoDTO editEmprestimo(Long id, EmprestimoDTO emprestimoDTO) {
        Emprestimo emprestimoEntity = emprestimoRep.findById(id).orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com o ID: " + id));
        emprestimoEntity.setUsuario(usuarioRep.findById(emprestimoDTO.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + emprestimoDTO.getUsuarioId())));
        emprestimoEntity.setLivro(livroRep.findById(emprestimoDTO.getLivroId()).orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + emprestimoDTO.getLivroId())));
        emprestimoEntity.setBiblioteca(bibliotecaRep.findById(emprestimoDTO.getBibliotecaId()).orElseThrow(() -> new RuntimeException("Biblioteca não encontrada com o ID: " + emprestimoDTO.getBibliotecaId())));
        emprestimoEntity.setData_inicio(emprestimoDTO.getData_inicio());
        emprestimoEntity.setData_fim(emprestimoDTO.getData_fim());
        emprestimoEntity.setSituacao(String.valueOf(emprestimoDTO.getSituacao()));
        emprestimoRep.save(emprestimoEntity);
        return convertEntityToDto(emprestimoEntity);
    }
}
