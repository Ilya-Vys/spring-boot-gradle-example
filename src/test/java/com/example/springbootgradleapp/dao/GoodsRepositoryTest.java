package com.example.springbootgradleapp.dao;

import com.example.springbootgradleapp.entities.Goods;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GoodsRepositoryTest {

    @Autowired
    GoodsRepository repository;

    @Sql("/sql/goods/shouldDeleteGoods.sql")
    @Test
    void shouldDeleteGoods(){
        Goods goods = repository.findById(100L).get();
        repository.delete(goods);
        assertThat(repository.findAll(),not(hasItem(goods)));
    }

    @Test
    void shouldSaveGoods() {
        Goods goods = new Goods("Suit", 100);
        repository.save(goods);
        assertThat(repository.findAll(), hasItem(goods));
    }

   /* @Sql("/sql/goods/shouldNotSaveGoodsWhenItAlreadyExist.sql")
    @Test
    void shouldNotSaveGoodsWhenItAlreadyExist() {
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(new Goods("Knife", 200));
        });
    }*/

    @Sql("/sql/goods/shouldGetAllGoods.sql")
    @Test
    void shouldGetAllAndAssertHavingGoods() {
        Goods goods1 = new Goods( "AK-47",30);
        Goods goods2 = new Goods( "AK-48",40);
        Goods goods3 = new Goods( "AK-49",50);
        List<Goods> actual = repository.findAll();
        assertThat(actual, hasItems(goods1, goods2, goods3));
    }

    @Sql("/sql/goods/shouldUpdateGoods.sql")
    @Test
    void shouldUpdateGoods(){
        assertEquals("Mars", repository.findById(66L).get().getName());
        repository.save(new Goods( 66L,"Snickers",30));
        assertEquals("Snickers", repository.findById(66L).get().getName());
    }


    @Sql("/sql/goods/shouldGetGoods.sql")
    @Test
    void shouldGetGoods(){
        Goods expected = new Goods(77L,"Bounty", 33);
        Goods actual = repository.getOne(77L);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
    }

    @Test
    void shouldNotGetGoods(){
        assertTrue(repository.findById(500L).isEmpty());
    }

}