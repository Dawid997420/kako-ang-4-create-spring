package com.example.kakoang4create.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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

    private String role ;
    public UserE(String email,String password ) {
        this.email = email ;
        this.password = password ;

    }

}

enum Sex {
    XX,XY,idk
}
