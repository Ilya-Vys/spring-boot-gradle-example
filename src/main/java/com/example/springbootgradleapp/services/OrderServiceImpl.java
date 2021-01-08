package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.dao.OrderRepository;
import com.example.springbootgradleapp.entities.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Order findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public void delete(Order order) {
        repository.delete(order);
    }

    @Override
    public List<Order> getAll() {
        return repository.findAll();
    }
}
