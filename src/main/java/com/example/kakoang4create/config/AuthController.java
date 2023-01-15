package com.example.kakoang4create.config;


import com.example.kakoang4create.model.UserELogin;
import com.example.kakoang4create.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {


    private static final Logger logger= LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public String token(@RequestBody UserELogin userE) {



        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userE.getEmail(),userE.getPassword()));


        String token = tokenService.generateToken(authentication);
        return token;
    }







}
