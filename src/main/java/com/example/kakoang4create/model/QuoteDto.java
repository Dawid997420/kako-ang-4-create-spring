package com.example.kakoang4create.model;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuoteDto {


    private String quote;

    private String image;


    private String author ;

}
