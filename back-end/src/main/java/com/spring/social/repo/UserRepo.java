package com.spring.social.repo;

import com.spring.social.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

     public User findByEmail(String email);

     public boolean existsByEmail(String email);
}
