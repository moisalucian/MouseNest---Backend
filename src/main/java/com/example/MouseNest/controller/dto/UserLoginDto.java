package com.example.MouseNest.controller.dto;

import com.example.MouseNest.constraint.FieldMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class UserLoginDto {

    @Email
    @NotEmpty
    private String email;

    @Size(min = 5, max = 30)
    private String password;



    public UserLoginDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
