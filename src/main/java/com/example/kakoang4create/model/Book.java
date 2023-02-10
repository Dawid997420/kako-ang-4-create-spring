package com.example.kakoang4create.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Book {

    @Id
    private String id ;

    private String title ;

    private String autor ;

    private String opis ;

    private Date rok;

    private String image ;

    private List<Category> categories ;




}

