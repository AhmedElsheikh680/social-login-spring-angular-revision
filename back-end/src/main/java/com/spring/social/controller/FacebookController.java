package com.spring.social.controller;

import com.spring.social.dto.TokenDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class FacebookController {


    //http://localhost:8080/api/v1/facebook
    @PostMapping("/facebook")
    public ResponseEntity<?> loginWithFacebook(@RequestBody TokenDTO tokenDTO){
        Facebook facebook = new FacebookTemplate(tokenDTO.getToken());
        String [] data = {"name", "email", "picture"};
        User user = facebook.fetchObject("me", User.class, data);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
