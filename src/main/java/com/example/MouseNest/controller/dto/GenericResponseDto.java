package com.example.MouseNest.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class GenericResponseDto {

    @Email
    @NotEmpty
    private boolean status;

    public GenericResponseDto() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
