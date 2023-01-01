package com.example.kakoang4create.model;


import lombok.Data;

@Data
public class UserELogin {


    private String email;

    private String password;

    public UserELogin(String email,String password) {
        this.email = email;
        this.password = password;
    }

}
