package com.example.kakoang4create.controller;


import com.example.kakoang4create.model.Comment;
import com.example.kakoang4create.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentController {


    @Autowired
    private CommentRepository commentRepository;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Comment getCommentById(@PathVariable String id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public Comment addComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }



}
