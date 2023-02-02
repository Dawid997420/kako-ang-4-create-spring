package com.example.kakoang4create.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Article {

    @Id
    private String id ;

    @NonNull
    private String topic ;



    private List<Paragraph> paragraphs;

    private List<Category> categories;

    private Date created;

    private List<Comment> comments;

    private String userId;


}
