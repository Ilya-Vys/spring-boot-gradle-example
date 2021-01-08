package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.entities.OrderLine;

import java.util.List;

public interface OrderLineService {

    OrderLine findById(Long id);

    OrderLine save(OrderLine orderLine);

    void delete(OrderLine orderLine);

    List<OrderLine> getAll();

    List<OrderLine> getByOrderId(Long id);
}
