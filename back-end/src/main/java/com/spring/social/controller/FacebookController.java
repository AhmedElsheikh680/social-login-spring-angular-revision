package com.spring.social.controller;

import com.spring.social.dto.TokenDTO;
import com.spring.social.model.Role;
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
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FacebookController {

    @Value("${mySecret.password}")
    private String password;

    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    private TokenService tokenService;

    @Autowired
    public FacebookController(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.tokenService = tokenService;
    }

    //http://localhost:8080/api/v1/facebook
    @PostMapping("/facebook")
    public ResponseEntity<LoginResponse> loginWithFacebook(@RequestBody TokenDTO tokenDTO) throws Exception {
        Facebook facebook = new FacebookTemplate(tokenDTO.getToken());
        String [] data = {"name", "email", "picture"};
        User user = facebook.fetchObject("me", User.class, data);



        com.spring.social.model.User userFace = new com.spring.social.model.User();
        String email = user.getEmail();

        if(userService.isEmailExists(email)){  // True =>  getemail
           userFace =  userService.getUserByEmail(email);
            System.out.println("Email Exists");

        } else {  // false => create new Account
            userFace = createUser(email);
            System.out.println("Email Not Exists");
        }

        ////////////////Login
        JwtLogin jwtLogin = new JwtLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);


        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), HttpStatus.OK);
    }

    private com.spring.social.model.User createUser(String email) {

        com.spring.social.model.User user = new com.spring.social.model.User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        List<Role> roles = roleService.getRoles();
        user.getRoles().add(roles.get(0));
        return userService.saveUser(user);
    }


}
