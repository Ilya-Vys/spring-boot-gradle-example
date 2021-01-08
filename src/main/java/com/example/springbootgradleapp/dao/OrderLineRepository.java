package com.example.springbootgradleapp.dao;

import com.example.springbootgradleapp.entities.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Long> {
    List<OrderLine> findOrderLineByOrderId(Long orderId);
}
