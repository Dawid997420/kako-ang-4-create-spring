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
        String topicToCheck = "";

        for ( int i =0 ; i < articleList.size() ; i++ ) {


            topicToCheck = articleList.get(i).getTopic().replace("?","");
            topicToCheck = topicToCheck.replace(" ","-");
            topicToCheck = topicToCheck.replace("ł","l");
            topicToCheck = topicToCheck.replace("ą","a");
            topicToCheck = topicToCheck.replace("ó","o");
            topicToCheck = topicToCheck.replace("ę","e");
            topicToCheck = topicToCheck.replace("ć","c");
            topicToCheck = topicToCheck.replace("ń","n");
            topicToCheck = topicToCheck.replace("ź","z");
            topicToCheck = topicToCheck.replace("ż","z");
            topicToCheck = topicToCheck.replace("ś","s");


            if ( topicToCheck.equals(topic) ) {

                foundArticle = articleList.get(i);
                break;
            }


        }

        return foundArticle;

    }

}
