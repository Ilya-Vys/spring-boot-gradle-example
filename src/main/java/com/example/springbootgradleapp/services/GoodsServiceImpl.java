package com.example.springbootgradleapp.services;

import com.example.springbootgradleapp.dao.GoodsRepository;
import com.example.springbootgradleapp.entities.Goods;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{

    private final GoodsRepository repository;

    public GoodsServiceImpl(GoodsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Goods findById(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Goods save(Goods goods) {
       return repository.save(goods);
    }

    @Override
    public void delete(Goods goods) {
        repository.delete(goods);
    }

    @Override
    public List<Goods> getAll() {
        return repository.findAll();
    }
}
