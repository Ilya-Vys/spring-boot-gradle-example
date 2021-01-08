package com.example.springbootgradleapp.dao;

import com.example.springbootgradleapp.entities.OrderLine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderLineRepositoryTest {

    @Autowired
    OrderLineRepository repository;

    @Sql("/sql/orderLines/shouldDeleteOrderLine.sql")
    @Test
    void shouldDeleteOrderLine() {
        OrderLine line = repository.findById(111L).get();
        System.out.println(line);
        repository.delete(line);
        assertThat(repository.findAll(), not(hasItem(line)));
    }

    @Test
    void shouldSaveOrderLine() {
        OrderLine line = new OrderLine(22L, 22L, 222);
        repository.save(line);
        assertThat(repository.findAll(), hasItem(line));

    }

    @Sql("/sql/orderLines/shouldGetAllAndAssertHavingOrderLines.sql")
    @Test
    void shouldGetAllAndAssertHavingOrderLines() {
        OrderLine line1 = new OrderLine(66L, 66L, 6);
        OrderLine line2 = new OrderLine(77L, 77L, 7);
        OrderLine line3 = new OrderLine(88L, 88L, 8);
        List<OrderLine> actual = repository.findAll();
        assertThat(actual, hasItems(line1, line2, line3));
    }

    @Sql("/sql/orderLines/shouldUpdateOrderLines.sql")
    @Test
    void shouldUpdateOrderLine() {
        OrderLine line = repository.findById(333L).get();
        assertEquals(33, line.getOrderId());
        assertEquals(33, line.getGoodsId());
        assertEquals(33, line.getCount());
        repository.save(new OrderLine(333L, 44L, 44L, 44));
        OrderLine actual = repository.findById(333L).get();
        assertEquals(44, actual.getOrderId());
        assertEquals(44, actual.getGoodsId());
        assertEquals(44, actual.getCount());
    }

    @Sql("/sql/orderLines/shouldGetOrderLines.sql")
    @Test
    void shouldGetOrderLine() {
        OrderLine expected = new OrderLine(123L, 123L, 123L, 123);
        OrderLine actual = repository.getOne(123L);
        assertEquals(expected.getOrderId(), actual.getOrderId());
        assertEquals(expected.getGoodsId(), actual.getGoodsId());
        assertEquals(expected.getCount(), actual.getCount());
    }

    @Test
    void shouldNotGetOrderLine(){
        assertTrue(repository.findById(500L).isEmpty());
    }

    @Sql("/sql/orderLines/shouldGetAllOrderLinesByOrderId.sql")
    @Test
    void shouldGetAllOrderLinesByOrderId(){
        List<OrderLine> actual = repository.findOrderLineByOrderId(321L);
        assertEquals(6, actual.size());
    }

}