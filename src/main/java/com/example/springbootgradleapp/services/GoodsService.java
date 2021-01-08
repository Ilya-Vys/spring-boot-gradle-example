package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.entities.Goods;

import java.util.List;

public interface GoodsService {

    Goods findById(Long id);

    Goods save(Goods goods);

    void delete(Goods goods);

    List<Goods> getAll();
}
