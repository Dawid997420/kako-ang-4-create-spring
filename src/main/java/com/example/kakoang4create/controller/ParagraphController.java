package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.model.Paragraph;
import com.example.kakoang4create.repository.ArticleRepository;
import com.example.kakoang4create.repository.ParagraphRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/paragraphs")
@RestController
public class ParagraphController {


    @Autowired
    ParagraphRepostiory paragraphRepostiory;

    @Autowired
    ArticleRepository articleRepository;


    @GetMapping
    public ResponseEntity<List<Paragraph>> getAllParagraphs() {
        return ResponseEntity.ok(paragraphRepostiory.findAll());
    }


    @PostMapping("/{index}/{id}")
    public ResponseEntity<Article> addParagraphToArticle(@PathVariable Integer index,@PathVariable String id ,@RequestBody Paragraph paragraph){

      Article articleToEdit = articleRepository.findById(id).orElseThrow();

      List<Paragraph> paragraphList = articleToEdit.getParagraphs();

      paragraphList.add(index,paragraph);

      articleToEdit.setParagraphs(paragraphList);

      articleRepository.save(articleToEdit);

      return  ResponseEntity.ok(articleToEdit);
    }




}
