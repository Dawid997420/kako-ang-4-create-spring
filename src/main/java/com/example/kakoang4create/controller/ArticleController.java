package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.model.TypePar;
import com.example.kakoang4create.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;




    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {

        return ResponseEntity.ok( articleRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable String id) {

        return ResponseEntity.ok(articleRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {

        article.getParagraphs().stream().map( paragraph ->  {
            if ( String.valueOf( paragraph.getType()).equalsIgnoreCase("font-plain")) {
                   paragraph.setType(TypePar.plain);
            } else if( String.valueOf( paragraph.getType()) == "font-bold") {
                    paragraph.setType(TypePar.bold);
            }  else if( String.valueOf( paragraph.getType()) == "font-subtitle") {
                    paragraph.setType(TypePar.subtitle);
            }
        return paragraph ;
        });


        article.setComments(new ArrayList<>());
        return ResponseEntity.ok(articleRepository.save(article));
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable String id ) {

        articleRepository.deleteById(id);

    }

    @DeleteMapping
    public void deleteAllArticles() {

        articleRepository.deleteAll();
    }





}
