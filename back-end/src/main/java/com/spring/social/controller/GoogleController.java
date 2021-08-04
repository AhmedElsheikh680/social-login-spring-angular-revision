package com.spring.social.controller;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.spring.social.dto.TokenDTO;
import com.spring.social.model.Role;
import com.spring.social.model.User;
import com.spring.social.security.jwt.JwtLogin;
import com.spring.social.security.jwt.LoginResponse;
import com.spring.social.security.jwt.TokenService;
import com.spring.social.service.RoleService;
import com.spring.social.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GoogleController {


    @Value("${mySecret.password}")
    private String password;

    @Value("${google.id}")
    private String clientId;



    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private TokenService tokenService;

    private String email;
    @Autowired
    public GoogleController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.tokenService = tokenService;
    }


    //http://localhost:8080/api/v1/google
    @PostMapping("/google")
    public ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws Exception {
        System.out.println("Ahmed");
        System.out.println("Pass "+ password);
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder verify = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singleton(clientId));

        GoogleIdToken googleIdToken = GoogleIdToken.parse(verify.getJsonFactory(), tokenDTO.getToken());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();

        email = payload.getEmail();
        User user = new User();
        if(userService.isEmailExists(email)){  // True =>  getemail
            user = userService.getUserByEmail(email);
            System.out.println("Email Exists");

        } else {  // false => create new Account
            user = createUser(email);
            System.out.println("Email Not Exists");
        }

        ////////////////Login
        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);


        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), HttpStatus.OK);
    }



    private User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = roleService.getRoles();
        user.getRoles().add(roles.get(0));

        return userService.saveUser(user);


    }

}
