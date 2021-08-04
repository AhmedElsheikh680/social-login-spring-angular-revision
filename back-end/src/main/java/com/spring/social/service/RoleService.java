package com.spring.social.service;

import com.spring.social.model.Role;
import com.spring.social.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepo;

    public List<Role> getRoles(){
        return roleRepo.findAll();
    }
}
