package com.librarylink.api.controller;

import com.librarylink.api.dto.LivroDTO;
import com.librarylink.api.models.Livro;
import com.librarylink.api.repository.LivroRepository;
import com.librarylink.api.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LivroController {

    @Autowired
    private LivroRepository livroRep;
    @Autowired
    private LivroService livroService;

    // Cadastrar vários livros - POST
    @PostMapping("/livros")
    public String criarLivros(@RequestBody List<LivroDTO> livros){
            try{
                livroService.saveAll(livros);
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
    public List<LivroDTO> LerTudoLivro() {
        return livroService.findAll();
    }

    // Ler uma por id - GET
    @GetMapping("/livro/{id}")
    public String LerBibliotecaPorId(@PathVariable("id") Long id) {
        Optional<LivroDTO> livro = livroService.findById(id);

        if (livro.isPresent()) {
            return livro.get().toString();
        } else {
            return "Biblioteca não encontrada com o ID: " + id;
        }
    }

    // Editar por id - PUT
    @PutMapping("/livro/{id}")
    public String editarLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        try {
            Livro livro = livroService.editLivro(id, livroDTO);
            return livro.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
