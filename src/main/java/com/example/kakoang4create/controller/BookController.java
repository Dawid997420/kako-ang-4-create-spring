package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.Book;
import com.example.kakoang4create.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/books")
@RestController
public class BookController {


    @Autowired
    BookRepository bookRepository;


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Book>> getBook(@PathVariable String id) {

        return ResponseEntity.ok(bookRepository.findAll());
    }

    @PostMapping
    public void saveBook(@RequestBody Book book) {

        bookRepository.save(book);
    }





}
