package com.librarylink.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarylink.api.models.Book;
import com.librarylink.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    // Create - POST
    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Book bookteste = mapper.readValue(book.toString(), Book.class);
            Book _book = bookRepository.save(book);
            return new ResponseEntity<>(_book, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Read All - GET
    @GetMapping("/book")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Read One - GET
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> bookData = bookRepository.findById(id);

        return bookData.map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update - PUT
    @PutMapping("/book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        Optional<Book> bookData = bookRepository.findById(id);

        if (bookData.isPresent()) {
            Book _book = bookData.get();
            _book.setLibrary(book.getLibrary());
            _book.setCodigo(book.getCodigo());
            _book.setCategoria(book.getCategoria());
            _book.setTittle(book.getTittle());
            _book.setAutor(book.getAutor());

            return new ResponseEntity<>(bookRepository.save(_book), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete - DELETE
    @DeleteMapping("/book/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        try {
            bookRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
