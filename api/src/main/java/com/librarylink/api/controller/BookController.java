package com.librarylink.api.controller;

import java.util.ArrayList;
import java.util.List;

import com.librarylink.api.models.Book;
import com.librarylink.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Api(value = "BookController")
@ResponseBody
@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("book")
    public String createStudent(@RequestParam String name) {
        bookRepository.save(new Book("nome", 123));
        return " Saved successfully";
    }

    @GetMapping("book")
    public List<Book> getAllStudents() {
        return (List<Book>) bookRepository.findAll();
    }
}




