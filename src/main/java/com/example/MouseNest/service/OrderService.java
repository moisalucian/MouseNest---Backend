package com.example.MouseNest.service;

import com.example.MouseNest.model.Order;
import com.example.MouseNest.model.Product;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    Optional<Order> getOrderById(Integer id);

    Order updateOrder(Order order);

    Order addOrder(Order order);

    void deleteOrder(Integer id);
}