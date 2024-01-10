package com.librarylink.api.controller;

import com.librarylink.api.models.Biblioteca;
import com.librarylink.api.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BibliotecaController {

    @Autowired
    private BibliotecaRepository bibliotecaRep;

    // Cadastrar várias bibliotecas - POST
    @PostMapping("/bibliotecas")
    public String criarBibliotecas(@RequestBody List<Biblioteca> bibliotecas){
        try{
            bibliotecaRep.saveAll(bibliotecas);
            return "Bibliotecas salvas com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Revise os valores e tente novamente. Erro: " + e.getMessage();
        }
    }

    //Deletar - Delete
    @DeleteMapping("/biblioteca/{id}")
    public String deletaBiblioteca(@PathVariable("id") Long id){
        try {
            bibliotecaRep.deleteById(id);
            return "Apagado com sucesso";
        } catch (Exception e) {
            return "Essa biblioteca não existe ou não pôde ser apagada" + e.getMessage();
        }
    }

    // Ler todas - GET
    @GetMapping("/biblioteca")
    public List<Biblioteca> LerTudoBiblioteca() {
        return bibliotecaRep.findAll();
    }

    // Ler uma por id - GET
    @GetMapping("/biblioteca/{id}")
    public String LerBibliotecaPorId(@PathVariable("id") Long id) {
        Optional<Biblioteca> biblioteca = bibliotecaRep.findById(id);

        if (biblioteca.isPresent()) {
            return biblioteca.get().toString();
        } else {
            return "Biblioteca não encontrada com o ID: " + id;
        }
    }

    // Editar por id - PUT
    @PutMapping("/biblioteca/{id}")
    public String editarBiblioteca(@PathVariable Long id, @RequestBody Biblioteca biblioteca) {
        try {
            Optional<Biblioteca> bibliotecaEntity = bibliotecaRep.findById(id);

            if (bibliotecaEntity.isPresent()) {
                Biblioteca bibliotecaAtualizada = bibliotecaEntity.get();
                bibliotecaAtualizada.setEndereco(biblioteca.getEndereco());
                bibliotecaAtualizada.setProprietario(biblioteca.getProprietario());
                bibliotecaAtualizada.setEndereco(biblioteca.getEndereco());

                bibliotecaRep.save(bibliotecaAtualizada);
                return "Biblioteca atualizada com sucesso";
            } else {
                return "Biblioteca não encontrada com o ID: " + id;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao atualizar a biblioteca: " + e.getMessage();
        }
    }
}
