package com.spring.social.security.jwt;

import com.auth0.jwt.JWT;
import com.spring.social.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;


import java.util.Date;

@Service
public class TokenService {

    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    private String generateToken(Authentication authResult) {

        // Grab principal
        UserPrinciple principal = (UserPrinciple) authResult.getPrincipal();
        System.out.println(principal.getUsername());

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
        return token;
    }

    public LoginResponse login(JwtLogin jwtLogin) throws Exception{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtLogin.getEmail(),
                jwtLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = generateToken(authenticate);
        return new LoginResponse(token);
    }
}