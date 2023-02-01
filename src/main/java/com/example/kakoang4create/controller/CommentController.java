package com.example.kakoang4create.controller;


import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.model.Comment;
import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.repository.ArticleRepository;
import com.example.kakoang4create.repository.CommentRepository;
import com.example.kakoang4create.repository.UserERepository;
import com.example.kakoang4create.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/comments")
@RestController
public class CommentController {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserERepository userERepository;

    @Autowired
    private CommentService commentService ;


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
    public ResponseEntity<Article> addComment(@RequestBody Comment comment ) {



        if ( articleRepository.existsById(comment.getArticleId()) && userERepository.existsById(comment.getUserId()) ) {

            Article article = articleRepository.findById(comment.getArticleId()).orElseThrow();
            UserE userE = userERepository.findById(comment.getUserId()).orElseThrow();

            List<Comment> articleComments = article.getComments();
            List<Comment> userComments = userE.getCommentList();

            userComments.add(comment);
            articleComments.add(comment);

            commentRepository.save(comment);

            article.setComments(articleComments);
            userE.setCommentList(userComments);


            articleRepository.save(article);
            userERepository.save(userE);



            return ResponseEntity.ok(article);

        } else {


            return ResponseEntity.badRequest().body(new Article());
        }

    }


    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteCommentById(@PathVariable String id ) {

        if ( commentRepository.existsById(id)) {


            commentService.deleteArticleComment(commentRepository.findById(id).orElseThrow().getUserId(),id);

            commentService.deleteUserComment(commentRepository.findById(id).orElseThrow().getArticleId(),id);


        commentRepository.deleteById(id);



        } else {

        }
      //  return ResponseEntity.ok(commentRepository.findById(id).orElseThrow());
    }


    @DeleteMapping
    public void deleteAllComments() {

        commentRepository.deleteAll();
        commentService.deleteAllCommentsFromUsers();
        commentService.deleteAllCommentsFromArticles();

    }





}
