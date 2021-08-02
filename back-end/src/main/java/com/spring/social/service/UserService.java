package com.spring.social.service;

import com.spring.social.model.User;
import com.spring.social.repo.UserRepo;
import com.spring.social.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails loadByEmail(String email){
        User user = userRepo.findByEmail(email);
        UserPrinciple userPrinciple = new UserPrinciple(user);
        return userPrinciple;
    }
}
