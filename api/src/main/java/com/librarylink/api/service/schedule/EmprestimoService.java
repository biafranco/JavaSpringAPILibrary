package com.librarylink.api.service.schedule;

import com.librarylink.api.dto.EmprestimoDTO;
import com.librarylink.api.models.Emprestimo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmprestimoService {
    public EmprestimoDTO convertEntityToDto(Emprestimo emprestimo);
    public Emprestimo convertDtoToEntity(EmprestimoDTO emprestimoDTO);

    Optional<EmprestimoDTO> findById(Long id);

    List<EmprestimoDTO> findAll();

    void deleteById(Long id);

    List<EmprestimoDTO> saveAll(List<EmprestimoDTO> emprestimos);

    EmprestimoDTO editEmprestimo(Long id, EmprestimoDTO emprestimoDTO);
}
