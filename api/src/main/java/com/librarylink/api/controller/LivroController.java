package com.librarylink.api.controller;

import com.librarylink.api.dto.LivroDTO;
import com.librarylink.api.service.schedule.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LivroController {

    @Autowired
    private LivroService livroService;

    // Cadastrar vários livros - POST
    @PostMapping("/livros")
    public ResponseEntity<String> createLivros(@RequestBody List<LivroDTO> livros){
        try{
            livroService.saveAll(livros);
            return ResponseEntity.status(HttpStatus.CREATED).body("Livros salvos com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Revise os valores e tente novamente. Erro: " + e.getMessage());
        }
    }
    //Deletar - Delete
    @DeleteMapping("/livro/{id}")
    public ResponseEntity<String> deleteLivro(@PathVariable("id") Long id){
        try {
            livroService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Apagado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse livro não existe ou não pôde ser apagada" + e.getMessage());
        }
    }

    // Ler todas - GET
    @GetMapping("/livros")
    public ResponseEntity<List<LivroDTO>> getAllLivros() {
        return ResponseEntity.status(HttpStatus.OK).body(livroService.findAll());
    }

    // Ler uma por id - GET
    @GetMapping("/livro/{id}")
    public ResponseEntity<LivroDTO> getLivroById(@PathVariable("id") Long id) {
        try {
            Optional<LivroDTO> livro = livroService.findById(id);
            if (livro.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(livro.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Editar por id - PUT
    @PutMapping("/livro/{id}")
    public ResponseEntity<LivroDTO> updateLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO) {
        try {
            LivroDTO livro = livroService.updateLivro(id, livroDTO);
            return ResponseEntity.status(HttpStatus.OK).body(livro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
