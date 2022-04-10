package com.example.MouseNest.controller.dto;

import com.sun.istack.NotNull;
import lombok.Data;

/**
 * DTO -> data transfer object
 */

@Data
public class ProductDto {

    @NotNull
    private String name;

    @NotNull
    private String brand;

    @NotNull
    private double weight;

    @NotNull
    private String size;

    @NotNull
    private String dimensions;

    @NotNull
    private String connectivity;

    @NotNull
    private String description;

    @NotNull
    private double price;

    @NotNull
    private String url;
}
