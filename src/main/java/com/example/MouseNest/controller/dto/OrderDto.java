package com.example.MouseNest.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {

    @NotNull
    private String numberTracking;

    @NotNull
    private String adress;

    @NotNull
    private Date orderDate;

}
