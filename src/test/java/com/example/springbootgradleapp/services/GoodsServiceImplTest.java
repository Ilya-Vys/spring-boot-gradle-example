package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.dao.GoodsRepository;
import com.example.springbootgradleapp.entities.Goods;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
class GoodsServiceImplTest {

    @Mock
    private GoodsRepository repository;

    @InjectMocks
    GoodsServiceImpl service;

    @Test
    void shouldSave(){
        Goods goods = new Goods("Shoes", 1);
        Goods savedGoods = new Goods(1L,"Shoes", 1);
        when(repository.save(goods)).thenReturn(savedGoods);
        assertEquals(savedGoods, service.save(goods));
        verify(repository, times(1)).save(any());

    }

    @Test
    void shouldDelete(){
        repository.delete(any());
        verify(repository, times(1)).delete(any());
    }

    @Test
    void shouldGet(){
        Goods goods = new Goods(1L,"Pencil", 10);
       // when(repository.getOne(anyLong())).thenReturn(goods);
        doReturn(goods).when(repository).getOne(1L);
        Goods actual = repository.getOne(1L);
        assertEquals(actual, goods);
        assertEquals(service.findById(1L), goods);
    }

    @Test
    void shouldGetAll(){
        List<Goods> allGoods = Arrays.asList(
                new Goods(2L, "2", 20),
                new Goods(3L, "3", 30),
                new Goods(4L, "4", 40));
        when(repository.findAll()).thenReturn(allGoods);
       // doReturn(allGoods).when(repository).findAll();
        List<Goods> actual = repository.findAll();
        assertEquals(3, actual.size());
    }
}