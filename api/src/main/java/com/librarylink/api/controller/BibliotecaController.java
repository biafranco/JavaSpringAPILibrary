package com.librarylink.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarylink.api.dto.BibliotecaDTO;
import com.librarylink.api.service.schedule.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    // Cadastrar várias bibliotecas - POST
    @PostMapping("/bibliotecas")
    public ResponseEntity<List<BibliotecaDTO>> createBibliotecas(@RequestBody List<BibliotecaDTO> bibliotecas){
        return new ResponseEntity<>(bibliotecaService.saveAll(bibliotecas), HttpStatus.CREATED);
    }

    //Deletar - Delete
    @DeleteMapping("/biblioteca/{id}")
    public ResponseEntity<String> deleteBiblioteca(@PathVariable("id") Long id){
        try {
            bibliotecaService.deleteById(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("This library does not exist or could not be deleted" + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Ler todas - GET
    @GetMapping("/biblioteca")
    public ResponseEntity<List<BibliotecaDTO>> getAllBibliotecas() {
        return new ResponseEntity<>(bibliotecaService.findAll(), HttpStatus.OK);
    }

    // Ler uma por id - GET
    @GetMapping("/biblioteca/{id}")
    public ResponseEntity<BibliotecaDTO> getBibliotecaById(@PathVariable("id") Long id) {
        try {
            Optional<BibliotecaDTO> bibliotecaDTO = bibliotecaService.findById(id);

            if (bibliotecaDTO.isPresent()) {
                return new ResponseEntity<>(bibliotecaDTO.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Editar por id - PUT
    @PutMapping("/biblioteca/{id}")
    public ResponseEntity<BibliotecaDTO> updateBiblioteca(@PathVariable Long id, @RequestBody BibliotecaDTO bibliotecaDTO) {
        try {
            BibliotecaDTO biblioteca = bibliotecaService.editBiblioteca(id, bibliotecaDTO);
            return new ResponseEntity<>(biblioteca, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}