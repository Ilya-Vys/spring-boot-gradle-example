package com.example.springbootgradleapp.dao;

import com.example.springbootgradleapp.entities.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;

    @Sql("/sql/orders/shouldDeleteOrder.sql")
    @Test
    void shouldDeleteOrder(){
        Order order = repository.findById(100L).get();
        repository.delete(order);
        assertThat(repository.findAll(),not(hasItem(order)) );
    }

    @Test
    void shouldSaveOrder(){
        Order order = new Order("Order15", LocalDateTime.now().minusHours(1L), "Earth");
        repository.save(order);
        assertThat(repository.findAll(), hasItem(order));
    }

    /*@Sql("/sql/orders/shouldNotSaveOrderWhenItAlreadyExist.sql")
    @Test
    void shouldNotSaveGoodsWhenItAlreadyExist() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(new Order("Order44"));
        });
    }*/

    @Sql("/sql/orders/shouldGetAllOrders.sql")
    @Test
    void shouldGetAllAndAssertHavingOrders() {
        Order order1 = new Order("Order41");
        Order order2 = new Order("Order42");
        Order order3 = new Order("Order43");
        List<Order> actual = repository.findAll();
        assertThat(actual, hasItems(order1, order2, order3));
    }

    @Sql("/sql/orders/shouldUpdateGoods.sql")
    @Test
    void shouldUpdateOrder(){
        assertEquals("Order555", repository.findById(61L).get().getName());
        repository.save(new Order( 61L,"Order444"));
        assertEquals("Order444", repository.findById(61L).get().getName());
    }

    @Sql("/sql/orders/shouldGetOrder.sql")
    @Test
    void shouldGetOrder(){
        Order expected = new Order("Order70");
        Order actual = repository.getOne(70L);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void shouldNotGetOrder(){
        assertTrue(repository.findById(500L).isEmpty());
    }

}