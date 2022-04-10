package com.example.MouseNest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "weight")
    private double weight;

    @Column(name = "size")
    private String size;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "connectivity")
    private String connectivity;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "img_url")
    private String url;

    @ManyToMany (mappedBy = "productList", cascade = CascadeType.ALL)
    private Set<Order> orderList;

}
