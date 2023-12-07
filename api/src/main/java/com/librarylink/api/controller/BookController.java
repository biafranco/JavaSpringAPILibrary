package com.librarylink.api.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@Api(value = "BookController")
@ResponseBody
@RestController
@RequestMapping("/v3/api")
public class BookController {
    public BookController() {
    }

    @Operation(summary = "Create a new book")
    @PostMapping("/book")
    public void createBook(@Valid @RequestBody String bookRequest) {}

    @ApiOperation(value = "List of all Books", response = ArrayList.class, tags = "book")
   @GetMapping(value = "/books")
    public void findAllBook() {}

    @GetMapping(value = "/book/{id}")
    public void findByID(@PathVariable("id") Long bookId) {}

    @Operation(summary = "Delete a Book")
    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) {}

    @Operation(summary = "Update a Book")
    @PutMapping("/book/{id}")
    public void updateBook(@PathVariable("id") Long usersId, @RequestBody String nada) {}
}