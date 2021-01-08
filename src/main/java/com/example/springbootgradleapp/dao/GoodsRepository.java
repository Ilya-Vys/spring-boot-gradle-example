package com.example.springbootgradleapp.dao;

import com.example.springbootgradleapp.entities.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
