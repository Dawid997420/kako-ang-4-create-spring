package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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


    @PostMapping
    public ResponseEntity<Boolean> registerUser(@RequestBody UserE userE) {

        if ( userERepository.findByEmail(userE.getEmail()).size() > 0) {



            return ResponseEntity.ok(false);

        } else {

            String passwordToSave = passwordEncoder.encode(userE.getPassword());

            UserE userEToSave = new UserE(userE.getEmail(),passwordToSave);

            userEToSave.setUsername(userE.getUsername());
            userEToSave.setBirthday(userE.getBirthday());
            userEToSave.setSex(userE.getSex());


            userEToSave.setRole("USER");

            userERepository.save(userEToSave);



            return ResponseEntity.ok(true);
        }


    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {

        userERepository.deleteById(id);

    }


    @GetMapping("/profile")
    public ResponseEntity<UserE> userInfo(Principal principal) {



        UserE userInfo = userERepository.findByEmail(principal.getName()).get(0);



        return ResponseEntity.ok(userInfo);

    }


}
