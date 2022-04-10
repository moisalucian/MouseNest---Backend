package com.example.MouseNest.service;

import com.example.MouseNest.model.Role;
import com.example.MouseNest.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void saveUser(String email, String password, Role role);

    User findByEmail(String email);

    boolean checkLogin(String email, String password);

}

