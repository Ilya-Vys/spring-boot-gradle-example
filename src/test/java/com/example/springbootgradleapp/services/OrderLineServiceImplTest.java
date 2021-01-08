package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.dao.OrderLineRepository;
import com.example.springbootgradleapp.entities.OrderLine;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@DataJpaTest
class OrderLineServiceImplTest {

    @Mock
    OrderLineRepository repository;

    @InjectMocks
    OrderLineServiceImpl service;

    @Test
    void shouldFindById() {
        OrderLine orderLine = new OrderLine(11L, 11L,11);
        when(repository.getOne(anyLong())).thenReturn(orderLine);
        assertEquals(service.findById(anyLong()), orderLine);
    }

    @Test
    void shouldSave() {
        OrderLine orderLine = new OrderLine(100L, 100L,100);
        OrderLine savedOrderLine = new OrderLine(100L,100L, 100L,100);
        when(repository.save(any())).thenReturn(savedOrderLine);
        assertEquals(savedOrderLine, service.save(orderLine));
        verify(repository, times(1)).save(any());
    }

    @Test
    void shouldDelete() {
        service.delete(any());
        verify(repository, times(1)).delete(any());
    }

    @Test
    void shouldGetAll() {
        List<OrderLine> expected = Arrays.asList(
                new OrderLine(55L, 55L, 55),
                new OrderLine(56L, 56L, 56),
                new OrderLine(57L, 57L, 57));
        when(repository.findAll()).thenReturn(expected);
        List<OrderLine> result = service.getAll();
        verify(repository, times(1)).findAll();
        assertEquals(result, expected);
    }

    @Test
    void shouldGetByOrderId() {
        List<OrderLine> expected = Arrays.asList(
                new OrderLine(155L, 155L, 155),
                new OrderLine(155L, 156L, 156),
                new OrderLine(155L, 157L, 157));
        when(repository.findOrderLineByOrderId(anyLong())).thenReturn(expected);
        List<OrderLine> result = service.getByOrderId(anyLong());
        verify(repository, times(1)).findOrderLineByOrderId(anyLong());
        assertEquals(result, expected);
    }
}