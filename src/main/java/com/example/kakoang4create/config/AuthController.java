package com.example.kakoang4create.config;

import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.service.TokenService;
import org.apache.el.parser.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/token")
    public String token(@RequestBody UserE userE) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userE.getUsername(),userE.getPassword()));


        String token = tokenService.generateToken(authentication);
        return token;
    }

}
