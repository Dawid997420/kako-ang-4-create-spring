package com.example.kakoang4create.config;

import com.example.kakoang4create.model.UserE;
import com.example.kakoang4create.repository.UserERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class LoginService implements UserDetailsService {


    @Autowired
    private UserERepository userERepository;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


       
        List<UserE> usersMatches = userERepository.findByEmail(email);

        if (  usersMatches.size() > 0 ) {

            Collection<GrantedAuthority> collection = new ArrayList<>();



            collection.add(new SimpleGrantedAuthority(usersMatches.get(0).getRole()));

            UserE userToCheck = usersMatches.get(0);

            return new User(userToCheck.getEmail(),
                 userToCheck.getPassword(),collection);
        } else {


            throw new UsernameNotFoundException("WRONG USER");
        }




    }


}
