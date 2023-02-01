package com.example.kakoang4create.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class UserE {

    @Id
    private String id ;

    @Email
    private String email;

    @Size(min = 5, max = 15)
    private String username;
    @Size(min = 8, max = 20)
    private String password ;

    private Sex sex ;

    private Date birthday ;


    private List<Article> articleList;

    private List<Comment> commentList ;

    private String role ;
    public UserE(String email,String password ) {
        this.email = email ;
        this.password = password ;
        this.setCommentList(new ArrayList<>());
        this.setArticleList(new ArrayList<>());

    }

}

enum Sex {
    XX,XY,idk
}
