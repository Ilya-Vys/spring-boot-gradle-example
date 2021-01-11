package com.example.springbootgradleapp.controllers;

import com.example.springbootgradleapp.entities.Goods;
import com.example.springbootgradleapp.services.GoodsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goods/")
@CrossOrigin(origins = "http://localhost:4200")
public class GoodsController {

    private final GoodsService service;

    public GoodsController(GoodsService service) {
        this.service = service;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Goods>> getAllGoods(){
        List<Goods> goods = service.getAll();
        if(goods.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goods,HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goods> getGoods(@PathVariable("id") Long goodsId){
        if(goodsId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Goods goods = service.findById(goodsId);

        if(goods == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goods> saveGoods(@RequestBody Goods goods){
        if(goods == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(goods);
        return new ResponseEntity<>(goods, HttpStatus.CREATED);
    }

    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goods> updateGoods(@RequestBody Goods goods){
        if(goods == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        service.save(goods);
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Goods> deleteGoods(@PathVariable("id") Long goodsId){
        Goods goods = service.findById(goodsId);
        if(goods == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(goods);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
