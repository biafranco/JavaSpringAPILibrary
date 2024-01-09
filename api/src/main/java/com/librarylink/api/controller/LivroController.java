package com.librarylink.api.controller;

import com.librarylink.api.models.Livro;
import com.librarylink.api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LivroController {

    @Autowired
    private LivroRepository livroRep;

    // Cadastrar vários livros - POST
    @PostMapping("/livros")
    public String criarLivros(@RequestBody List<Livro> livros){
        try{
            livroRep.saveAll(livros);
            return "Livros salvos com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Revise os valores e tente novamente. Erro: " + e.getMessage();
        }
    }

    //Deletar - Delete
    @DeleteMapping("/livro/{id}")
    public String deletaBiblioteca(@PathVariable("id") Long id){
        try {
            livroRep.deleteById(id);
            return "Apagado com sucesso";
        } catch (Exception e) {
            return "Esse livro não existe ou não pôde ser apagada" + e.getMessage();
        }
    }

    // Ler todas - GET
    @GetMapping("/livro")
    public List<Livro> LerTudoLivro() {
        return livroRep.findAll();
    }

    // Ler uma por id - GET
    @GetMapping("/livro/{id}")
    public String LerBibliotecaPorId(@PathVariable("id") Long id) {
        Optional<Livro> livro = livroRep.findById(id);

        if (livro.isPresent()) {
            return livro.get().toString();
        } else {
            return "Biblioteca não encontrada com o ID: " + id;
        }
    }

    // Editar por id - PUT
    @PutMapping("/livro/{id}")
    public String editarLivro(@PathVariable Long id, @RequestBody Livro livro) {
        try {
            Optional<Livro> livroEntity = livroRep.findById(id);

            if (livroEntity.isPresent()) {
                Livro livroAtualizado = livroEntity.get();
                livroAtualizado.setTitulo(livro.getTitulo());
                livroAtualizado.setAutor(livro.getAutor());
                livroAtualizado.setCategoria(livro.getCategoria());
                livroAtualizado.setData_lancamento(livro.getData_lancamento());
                livroAtualizado.setData_compra(livro.getData_compra());
                livro.setNumero_pagina(livro.getNumero_pagina());
                livroAtualizado.setBiblioteca(livro.getBiblioteca());

                livroRep.save(livroAtualizado);
                return "Livro atualizado com sucesso";
            } else {
                return "Livro não encontrada com o ID: " + id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar a livro: " + e.getMessage();
        }
    }
}
