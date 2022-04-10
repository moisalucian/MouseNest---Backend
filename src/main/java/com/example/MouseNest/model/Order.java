package com.example.MouseNest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id_order")
    private int id;

    @Column (name = "number_tracking")
    private String numberTracking;

    @Column (name = "address")
    private String address;

    @Column (name = "order_date")
    private Date orderDate;

    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn (name = "id_product")
    )
    private List<Product> productList = new ArrayList<>();

}
