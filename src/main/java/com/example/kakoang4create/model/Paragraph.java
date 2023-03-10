package com.example.kakoang4create.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Document
@Data
public class Paragraph {


    @Id
    private String id ;
    private String text ;
    private TypePar type;

    private String articleId;

}

