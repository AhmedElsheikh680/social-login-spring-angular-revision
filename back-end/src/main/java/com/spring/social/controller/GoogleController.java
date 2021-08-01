package com.spring.social.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.spring.social.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("api/v1")
public class GoogleController {

    @Value("${google.id}")
    private String clientId;

    //http://localhost:8080/api/v1/google
    @PostMapping("/google")
    public ResponseEntity<?> loginWithGoogle(@RequestBody TokenDTO tokenDTO) throws IOException {
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier.Builder verify = new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
                .setAudience(Collections.singleton(clientId));

        GoogleIdToken googleIdToken = GoogleIdToken.parse(verify.getJsonFactory(), tokenDTO.getToken());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}
