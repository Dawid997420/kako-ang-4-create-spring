package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users")
@RestController
public class UserEController {

    @Autowired
    private UserERepository userERepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<UserE> getUsers() {

        return userERepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void registerUser(@RequestBody UserE userE) {

        if ( userERepository.findByUsername(userE.getUsername()).size() > 0) {




        } else {

            String passwordToSave = passwordEncoder.encode(userE.getPassword());

            UserE userEToSave = new UserE(userE.getUsername(),passwordToSave);

            userERepository.save(userEToSave);

        }


    }

}
