package com.example.kakoang4create.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Quote {


    @Id
    private String id ;
    private String quote;
    
    private String image;

    private String author;

}
