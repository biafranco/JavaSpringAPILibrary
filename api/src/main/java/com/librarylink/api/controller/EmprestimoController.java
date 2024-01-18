package com.librarylink.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarylink.api.dto.BibliotecaDTO;
import com.librarylink.api.dto.EmprestimoDTO;
import com.librarylink.api.service.schedule.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    // Cadastrar vários - POST
    @PostMapping("/emprestimos")
    public ResponseEntity<List<EmprestimoDTO>> createEmprestimos(@RequestBody List<EmprestimoDTO> emprestimos){
        return new ResponseEntity<>(emprestimoService.saveAll(emprestimos), HttpStatus.CREATED);
    }

    // Deletar - Delete
    @DeleteMapping("/emprestimos/{id}")
    public ResponseEntity<String> deleteEmprestimo(@PathVariable Long id) {
        try {
            emprestimoService.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("This loan does not exist or could not be deleted" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Ler todos - GET
    @GetMapping("/emprestimos")
    public ResponseEntity<List<EmprestimoDTO>> getAllEmprestimos() {
        return new ResponseEntity<>(emprestimoService.findAll(), HttpStatus.OK);
    }

    // Ler uma por ID - GET
    @GetMapping("/emprestimos/{id}")
    public ResponseEntity<EmprestimoDTO> getEmprestimoById(@PathVariable Long id) {
        try {
            Optional<EmprestimoDTO> emprestimo = emprestimoService.findById(id);
            if (emprestimo.isPresent()) {
                return new ResponseEntity<>(emprestimo.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Editar por Id - PUT
    @PutMapping("/emprestimos/{id}")
    public ResponseEntity<EmprestimoDTO> updateEmprestimo(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            EmprestimoDTO emprestimo = emprestimoService.editEmprestimo(id, emprestimoDTO);
            return new ResponseEntity<>(emprestimo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}