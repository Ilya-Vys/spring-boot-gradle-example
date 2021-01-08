package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.dao.OrderLineRepository;
import com.example.springbootgradleapp.entities.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineServiceImpl implements OrderLineService{

    private final OrderLineRepository repository;

    public OrderLineServiceImpl(OrderLineRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderLine findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public OrderLine save(OrderLine orderLine) {
        return repository.save(orderLine);
    }

    @Override
    public void delete(OrderLine orderLine) {
        repository.delete(orderLine);
    }

    @Override
    public List<OrderLine> getAll() {
        return repository.findAll();
    }

    @Override
    public List<OrderLine> getByOrderId(Long orderId) {
        return repository.findOrderLineByOrderId(orderId);
    }
}
