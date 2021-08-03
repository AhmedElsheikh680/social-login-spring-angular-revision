//package com.spring.social.bootstrap;
//
//import com.spring.social.model.User;
//import com.spring.social.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class Runnable implements CommandLineRunner {
//
//    private UserRepo userRepo;
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public Runnable(UserRepo userRepo, PasswordEncoder passwordEncoder) {
//        this.userRepo = userRepo;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        User user = new User();
//        user.setEmail("ahmedelsheikh680@gmail.com");
//        user.setPassword(passwordEncoder.encode("ahmed123"));
//        userRepo.save(user);
//
//        User user2 = new User();
//        user2.setEmail("eslam@gmail.com");
//        user2.setPassword(passwordEncoder.encode("eslam123"));
//        userRepo.save(user2);
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
