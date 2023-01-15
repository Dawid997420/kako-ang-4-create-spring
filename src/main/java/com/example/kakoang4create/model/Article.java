package com.example.kakoang4create.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Article {

    @Id
    private String id ;

    private String topic ;

    private List<Paragraph> paragraphs;

    private List<Category> categories;

    private Date created;

    private List<Comment> comments;

    private UserE author;


}
