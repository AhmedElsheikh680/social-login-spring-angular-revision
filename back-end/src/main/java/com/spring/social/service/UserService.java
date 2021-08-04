package com.spring.social.service;

import com.spring.social.model.User;
import com.spring.social.repo.UserRepo;
import com.spring.social.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        UserPrinciple userPrinciple = new UserPrinciple(user);
        return userPrinciple;
    }


    public boolean isEmailExists(String email){
        return userRepo.existsByEmail(email);
    }

    public User  getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }
}
