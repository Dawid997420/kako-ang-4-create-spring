package com.example.kakoang4create.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;


    public String generateToken(Authentication authentication) {



        int timeToLogin = 1;

        if (authentication.getAuthorities().iterator().next().equals(new SimpleGrantedAuthority("ADMIN"))) {
             timeToLogin= 24;
        }//

        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet. builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(timeToLogin, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope",scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}
