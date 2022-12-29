package com.example.kakoang4create.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserE {

    @Id
    private String id ;

    private String username ;

    private String password ;


    public UserE(String username,String password ) {
        this.username = username ;
        this.password = password ;

    }

}
