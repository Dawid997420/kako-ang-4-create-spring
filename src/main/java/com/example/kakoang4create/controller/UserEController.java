package com.example.kakoang4create.controller;

import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.repository.UserERepository;
import org.apache.catalina.User;
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


    @GetMapping("/{id}")
    public ResponseEntity<UserE> getUserById(@PathVariable String id) {

        return ResponseEntity.ok(userERepository.findById(id).orElseThrow());

    }

    @PutMapping
    public ResponseEntity<UserE> changeUserImg (@RequestBody UserE userE) {

        userERepository.save(userE);
        return  ResponseEntity.ok(userE);
    }

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

    @PostMapping("/{secret}")
    public ResponseEntity<Boolean> registerAdmin(@RequestBody UserE userE, @PathVariable String secret ) {

        if ( userERepository.findByEmail(userE.getEmail()).size() > 0) {



            return ResponseEntity.ok(false);

        } else {

            ////        $2a$10$v.o/uilr7mFOJotYqe9nc.9kaIO/AQMbEQL4M2RwFjUzprVuRz9wW
            ////          997420123essa
            if ( passwordEncoder.matches(secret,"$2a$10$v.o/uilr7mFOJotYqe9nc.9kaIO/AQMbEQL4M2RwFjUzprVuRz9wW")) {
                System.out.println("esssssaaaaaaa");


                String passwordToSave = passwordEncoder.encode(userE.getPassword());

                UserE userEToSave = new UserE(userE.getEmail(), passwordToSave);

                userEToSave.setUsername(userE.getUsername());
                userEToSave.setBirthday(userE.getBirthday());
                userEToSave.setSex(userE.getSex());


                userEToSave.setRole("ADMIN");

                userERepository.save(userEToSave);

                return ResponseEntity.ok(true);

            } else {
                return ResponseEntity.ok(false);
            }

        }



    }


    @PatchMapping("/{role}")
    public ResponseEntity<UserE> changeRoleToUser(@RequestBody UserE userE ,@PathVariable String role) {



        userE.setRole(role);

        userERepository.save(userE);
        return ResponseEntity.ok(userE);
    }



    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {

        userERepository.deleteById(id);

    }


    @DeleteMapping
    public void deleteAll() {

        userERepository.deleteAll();
    }


    @GetMapping("/profile")
    public ResponseEntity<UserE> userInfo(Principal principal) {



        UserE userInfo = userERepository.findByEmail(principal.getName()).get(0);



        return ResponseEntity.ok(userInfo);

    }


}
