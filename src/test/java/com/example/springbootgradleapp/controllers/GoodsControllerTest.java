package com.example.springbootgradleapp.controllers;

import com.example.springbootgradleapp.entities.Goods;
import com.example.springbootgradleapp.services.GoodsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.mockito.Mockito.when;


@WebMvcTest(GoodsController.class)
class GoodsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GoodsServiceImpl service;

    @Test
    void shouldGetGoods() throws Exception {
        Goods goods = new Goods(111L, "111", 111);
        given(service.findById(111L)).willReturn(goods);
        mockMvc.perform(get("/api/goods/" + goods.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(111)))
                .andExpect(jsonPath("$.name", is("111")))
                .andExpect(jsonPath("$.price", is(111)));
    }

    @Test
    void shouldNotGetGoods() throws Exception {
        given(service.findById(22L)).willReturn(null);
        mockMvc.perform(get("/api/goods/22")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteGoods() throws Exception {
        Goods goods = new Goods(333L, "333", 333);
        given(service.findById(333L)).willReturn(goods);
        doNothing().when(service).delete(goods);
        mockMvc.perform(delete("/api/goods/333")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldNotFoundGoodsForDeleting() throws Exception {
        Goods goods = new Goods(555L, "555", 555);
        given(service.findById(555L)).willReturn(null);
        doNothing().when(service).delete(goods);
        mockMvc.perform(delete("/api/goods/333")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindAll() throws Exception {
        Goods goods1 = new Goods(555L, "555", 555);
        Goods goods2 = new Goods(666L, "666", 666);
        Goods goods3 = new Goods(777L, "777", 777);
        List<Goods> goods = Arrays.asList(goods1, goods2, goods3);
        when(service.getAll()).thenReturn(goods);
        mockMvc.perform(get("/api/goods/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}