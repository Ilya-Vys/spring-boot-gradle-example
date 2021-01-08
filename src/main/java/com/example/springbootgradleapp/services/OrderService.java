package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.entities.Order;

import java.util.List;

public interface OrderService {

    Order findById(Long id);

    Order save(Order order);

    void delete(Order order);

    List<Order> getAll();
}
