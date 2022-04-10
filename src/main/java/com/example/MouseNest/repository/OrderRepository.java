package com.example.MouseNest.repository;

import com.example.MouseNest.model.Order;
import com.example.MouseNest.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAll();

    Optional<Order> findOrderById(Integer id);

    Optional<Order> findOrderByOrderDate(Date date);

}
