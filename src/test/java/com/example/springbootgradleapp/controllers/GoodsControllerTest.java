package com.example.springbootgradleapp.controllers;

import com.example.springbootgradleapp.entities.Goods;
import com.example.springbootgradleapp.services.GoodsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(555)))
                .andExpect(jsonPath("$[0].name", is("555")))
                .andExpect(jsonPath("$[0].price", is(555)))
                .andExpect(jsonPath("$[1].id", is(666)))
                .andExpect(jsonPath("$[1].name", is("666")))
                .andExpect(jsonPath("$[1].price", is(666)))
                .andExpect(jsonPath("$[2].id", is(777)))
                .andExpect(jsonPath("$[2].name", is("777")))
                .andExpect(jsonPath("$[2].price", is(777)));
    }

    @Test
    void shouldReturnNotFoundWhenHaveNoGoods() throws Exception {
        when(service.getAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/goods/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldSaveGoods() throws Exception {
        Goods goods2 = new Goods(888L,  "888", 888);
        given(service.save(any())).willReturn(goods2);
        mockMvc.perform(post("/api/goods/")
                .content("{\"name\": \"888\", \"price\": \"888\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    void shouldUpdateGoods() throws Exception {
        mockMvc.perform(put("/api/goods/")
                .content("{\"id\": \"999\", \"name\": \"999\", \"price\": \"999\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("999"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("999"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("999"));
    }

}