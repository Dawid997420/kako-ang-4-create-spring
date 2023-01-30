package com.example.kakoang4create.service;

import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {


    @Autowired
    public ArticleRepository articleRepository;

    public Article findByTopic(String topic ) {

        List<Article> articleList = articleRepository.findAll();
        Article foundArticle = new Article();

        for ( int i =0 ; i < articleList.size() ; i++ ) {

            if ( articleList.get(i).getTopic().equals(topic) ) {

                foundArticle = articleList.get(i);
                break;
            }


        }

        return foundArticle;

    }

}
