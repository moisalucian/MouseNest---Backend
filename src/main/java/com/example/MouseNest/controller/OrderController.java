package com.example.MouseNest.controller;

import com.example.MouseNest.controller.dto.OrderDto;
import com.example.MouseNest.model.Order;
import com.example.MouseNest.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/orders")
@Controller
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @GetMapping("/allOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orderList = orderServiceImpl.getAllOrders();
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Integer id) {
        try {
            Optional<Order> order = orderServiceImpl.getOrderById(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderServiceImpl.addOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody OrderDto orderDto, @PathVariable(value = "id") Integer id) {
        Optional<Order> orderFromDatabase = orderServiceImpl.getOrderById(id);
        orderFromDatabase.get().setNumberTracking(orderDto.getNumberTracking());
        orderFromDatabase.get().setAddress(orderDto.getAdress());
        orderFromDatabase.get().setOrderDate(orderDto.getOrderDate());
        orderServiceImpl.updateOrder(orderFromDatabase.get());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id) {
        try {
            orderServiceImpl.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
