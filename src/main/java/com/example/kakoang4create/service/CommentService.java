package com.example.kakoang4create.service;


import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.model.Comment;
import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.repository.ArticleRepository;
import com.example.kakoang4create.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {


    @Autowired
    UserERepository userERepository;

    @Autowired
    ArticleRepository articleRepository;

    public void deleteUserComment(String userId,String commentId) {

        UserE userE =userERepository.findById(userId).orElseThrow();
        List<Comment> newCommentList = userE.getCommentList();

        for ( int i = 0 ; i < userE.getCommentList().size() ; i++) {

            if ( userE.getCommentList().get(i).getId().equals(commentId)) {

                newCommentList.remove(i);
                break;
            }
        }

        userE.setCommentList(newCommentList);
        userERepository.save(userE);

    }
    public void deleteArticleComment(String articleId , String commentId) {

        Article article =articleRepository.findById(articleId).orElseThrow();
        List<Comment> newCommentList = article.getComments();

        for ( int i = 0 ; i < article.getComments().size() ; i++) {

            if ( article.getComments().get(i).getId().equals(commentId)) {

                newCommentList.remove(i);
                break;
            }
        }

        article.setComments(newCommentList);
        articleRepository.save(article);

    }


    public void deleteAllCommentsFromArticles() {

        List<Article> articleList = articleRepository.findAll();
        List<Comment> newCommentList = new ArrayList<>();

        for ( int i =0; i< articleList.size() ; i++  ) {

            articleList.get(i).setComments(newCommentList);

        }

        articleRepository.saveAll(articleList);

    }

    public void deleteAllCommentsFromUsers() {

        List<UserE> usersList = userERepository.findAll();
        List<Comment> newCommentList = new ArrayList<>();

        for ( int i =0; i< usersList.size() ; i++  ) {

            usersList.get(i).setCommentList(newCommentList);

        }

        userERepository.saveAll(usersList);

    }



}
