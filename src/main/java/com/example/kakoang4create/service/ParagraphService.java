package com.example.kakoang4create.service;


import com.example.kakoang4create.model.Article;
import com.example.kakoang4create.model.Paragraph;
import com.example.kakoang4create.repository.ParagraphRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParagraphService {


    @Autowired
    ParagraphRepostiory paragraphRepostiory;


    public List<Paragraph> setArticleId(List<Paragraph> paragraphList, Article article) {

      List<Paragraph> paragraphListToSave = new ArrayList<>();
     /*   paragraphListToSave =  paragraphList.stream().map(paragraph ->{
                  paragraph.setArticleId(article.getId());
                  return  paragraph;

        }).toList();*/

        for ( int i = 0 ; i < paragraphList.size() ; i++) {

            paragraphList.get(i).setArticleId(article.getId());


        }



      return  paragraphList;
    }

    public void saveAllParagraphs(List<Paragraph> paragraphList ,Article article) {

        List<Paragraph> paragraphListToSave = new ArrayList<>();


        paragraphListToSave = setArticleId(paragraphList,article );


        for ( int i = 0 ; i < paragraphList.size() ; i++) {

            paragraphRepostiory.save(paragraphListToSave.get(i));

        }
    }


    public void deleteAllArticlesParagraphs(List<Paragraph> paragraphList) {

        paragraphRepostiory.deleteAll(paragraphList);

    }


}
