package com.example.springbootgradleapp.dao;

import com.example.springbootgradleapp.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
