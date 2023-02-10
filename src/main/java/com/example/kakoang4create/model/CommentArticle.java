package com.example.kakoang4create.model;

import lombok.Data;

@Data
public class CommentArticle {

    public CommentArticle(){}
    public CommentArticle(String id,String username,String text,String articleId, String userId ){
        this.id= id;
        this.username = username;
        this.text = text;
        this.articleId = articleId;
        this.userId = userId;
    }
    private String id ;
    private String image;

    private String username;

    private String text;

    private String articleId;

    private String userId;


}
