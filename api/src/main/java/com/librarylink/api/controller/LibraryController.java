package com.librarylink.api.controller;

import com.librarylink.api.models.Library;
import com.librarylink.api.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {

    @Autowired
    private LibraryRepository libraryRepository;

    // Create - POST
    @PostMapping("/library")
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        try {
            Library _library = libraryRepository.save(new Library(library.getCodigo(), library.getEndereco(), library.getProprietario(), library.getTelefone()));
            return new ResponseEntity<>(_library, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read All - GET
    @GetMapping("/library")
    public List<Library> getAllLibraries() {
        return libraryRepository.findAll();
    }

    // Read One - GET
    @GetMapping("/library/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable("id") Long id) {
        Optional<Library> libraryData = libraryRepository.findById(id);

        return libraryData.map(library -> new ResponseEntity<>(library, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update - PUT
    @PutMapping("/library/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable("id") Long id, @RequestBody Library library) {
        Optional<Library> libraryData = libraryRepository.findById(id);

        if (libraryData.isPresent()) {
            Library _library = libraryData.get();
            _library.setCodigo(library.getCodigo());
            _library.setEndereco(library.getEndereco());
            _library.setProprietario(library.getProprietario());
            _library.setTelefone(library.getTelefone());

            return new ResponseEntity<>(libraryRepository.save(_library), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - DELETE
    @DeleteMapping("/library/{id}")
    public ResponseEntity<HttpStatus> deleteLibrary(@PathVariable("id") Long id) {
        try {
            libraryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
