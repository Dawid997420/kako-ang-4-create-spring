package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.model.Paragraph;
import com.example.kakoang4create.model.TypePar;
import com.example.kakoang4create.repository.ArticleRepository;
import com.example.kakoang4create.repository.ParagraphRepostiory;
import com.example.kakoang4create.service.ArticleService;
import com.example.kakoang4create.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/articles")
@RestController
public class ArticleController {


    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ParagraphService paragraphService ;

    @Autowired
    private ParagraphRepostiory paragraphRepostiory;

    @Autowired
    private ArticleService articleService;

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

        article.setComments(new ArrayList<>());
        articleRepository.save(article);

        paragraphService.saveAllParagraphs(article.getParagraphs(),article);

       articleRepository.save(article);


        return ResponseEntity.ok(article);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable String id ) {


        if ( articleRepository.findById(id).orElseThrow().getParagraphs() != null)  {
            paragraphService.deleteAllArticlesParagraphs(articleRepository.findById(id).orElseThrow().getParagraphs());
        }

        articleRepository.deleteById(id);

    }


    @GetMapping("/topic/{topic}")
    public ResponseEntity<Article> getArticleFromTopic(@PathVariable String topic) {



        Article articleFind = articleService.findByTopic(topic);



       return ResponseEntity.ok(articleFind);

    }


    @DeleteMapping
    public void deleteAllArticles() {

        paragraphRepostiory.deleteAll();
        articleRepository.deleteAll();

    }



    @GetMapping("/category/{category}")
    public ResponseEntity<List<Article>> getArticle(@PathVariable String category){


       List<Article> allCat = articleRepository.findAllBycategories(category);

       return ResponseEntity.ok(allCat);
    }





}
