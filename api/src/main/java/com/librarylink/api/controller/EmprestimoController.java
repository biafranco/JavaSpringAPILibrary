package com.librarylink.api.controller;

import com.librarylink.api.models.Emprestimo;
import com.librarylink.api.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRep;

    // Cadastrar vários - POST
    @PostMapping("/emprestimo")
    public List<Emprestimo> criarEmprestimos(@RequestBody List<Emprestimo> emprestimos){
        return emprestimoRep.saveAll(emprestimos);
    }

    // Deletar - Delete
    @DeleteMapping("/empretimo/{id}")
    public String deletaEmprestimo(@PathVariable Long id) {
        try {
            emprestimoRep.deleteById(id);
            return "Apagado com sucesso";
        } catch (Exception e) {
            return "Esse emprestimo não existe ou não pôde ser apagado" + e.getMessage();
        }
    }

    // Ler todos - GET
    @GetMapping("/emprestimo")
    public List<Emprestimo> LerTudoEmprestimo() {
        return emprestimoRep.findAll();
    }

    // Ler uma por ID - GET
    @GetMapping("/emprestimo/{id}")
    public String LerEmprestimoPorId(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRep.findById(id);
        if (emprestimo.isPresent()) {
            return emprestimo.get().toString();
        } else {
            return "Biblioteca não encontrada com o ID: " + id;
        }
    }

    // Editar por Id - PUT
    @PutMapping("/emprestimo/{id}")
    public String editarEmprestimo(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        try {
            Optional<Emprestimo> emprestimoEntity = emprestimoRep.findById(id);
            
            if (emprestimoEntity.isPresent()) {
                Emprestimo emprestimoAtualizado = emprestimoEntity.get();
                emprestimoAtualizado.setUsuario(emprestimo.getUsuario());
                emprestimoAtualizado.setLivro(emprestimo.getLivro());
                emprestimoAtualizado.setBiblioteca(emprestimo.getBiblioteca());
                emprestimoAtualizado.setData_inicio(emprestimo.getData_inicio());
                emprestimoAtualizado.setData_fim(emprestimo.getData_fim());
                emprestimoAtualizado.setSituacao(emprestimo.getSituacao());

                emprestimoRep.save(emprestimoAtualizado);
                return "emprestimo atualizado";
            } else {
                return "nao encontrado com o id" + id;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar a livro: " + e.getMessage();
        }

    }
}