package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.dao.OrderRepository;
import com.example.springbootgradleapp.entities.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@DataJpaTest
class OrderServiceImplTest {

    @Mock
    OrderRepository repository;

    @InjectMocks
    OrderServiceImpl service;


    @Test
    void shouldGet() {
        Order order = new Order(10L, "Order10");
        doReturn(order).when(repository).getOne(10L);
        Order actual = repository.getOne(10L);
        assertEquals(actual, order);
        assertEquals(service.findById(10L), order);
    }

    @Test
    void shouldSave() {
        Order order = new Order(100L,"Order20", LocalDateTime.of(2020, 12, 20,
                0, 0, 0, 0), "Earth");
        when(repository.save(any())).thenReturn(order);
        Order result = service.save(any());
        assertEquals(result, order);
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldDelete() {
        service.delete(any());
        verify(repository, times(1)).delete(any());
    }

    @Test
    void shouldGetAll() {
        List<Order> orders = Arrays.asList(
              new Order("Order22"),
              new Order("Order33"),
              new Order("Order44"));
        when(repository.findAll()).thenReturn(orders);
        List<Order> actual = service.getAll();
        assertEquals(3, actual.size());
    }
}