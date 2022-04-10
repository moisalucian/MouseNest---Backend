package com.example.MouseNest.service;

import com.example.MouseNest.model.Role;
import com.example.MouseNest.model.User;
import com.example.MouseNest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(String email, String password, Role role) {
        User user = new User();

        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));//se codeaza parola
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean checkLogin(String email, String password) {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password !");
        }else{
            return passwordEncoder.matches(password, user.getPassword());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException("Invalid email or password !");
        }else{
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRole())
            );
        }
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role)
    {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

}
