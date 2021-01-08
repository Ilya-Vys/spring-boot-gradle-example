package com.example.springbootgradleapp;

import com.example.springbootgradleapp.dao.GoodsRepository;
import com.example.springbootgradleapp.dao.OrderLineRepository;
import com.example.springbootgradleapp.dao.OrderRepository;
import com.example.springbootgradleapp.entities.Goods;
import com.example.springbootgradleapp.entities.Order;
import com.example.springbootgradleapp.entities.OrderLine;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private GoodsRepository goodsRepository;
    private OrderRepository orderRepository;
    private OrderLineRepository orderLineRepository;

    public DevBootstrap(GoodsRepository goodsRepository, OrderRepository orderRepository,
                        OrderLineRepository orderLineRepository) {
        this.goodsRepository = goodsRepository;
        this.orderRepository = orderRepository;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {
        Goods goods1 = new Goods("Hat", 50);
        Goods goods2 = new Goods("Jeans", 300);
        Goods goods3 = new Goods("Shirt", 150);
        Goods goods4 = new Goods("Hood", 250);
        Goods goods5 = new Goods("Jacket", 400);

        goods1 = goodsRepository.save(goods1);
        goods2 = goodsRepository.save(goods2);
        goods3 = goodsRepository.save(goods3);
        goods4 = goodsRepository.save(goods4);
        goods5 = goodsRepository.save(goods5);


        Order order1 = new Order("Order1", LocalDateTime.now().minusDays(5), "AnyAddress1");
        Order order2 = new Order("Order2", LocalDateTime.now().minusDays(5), "AnyAddress2");
        Order order3 = new Order("Order3", LocalDateTime.now().minusDays(5), "AnyAddress3");
        Order order4 = new Order("Order4", LocalDateTime.now().minusDays(5), "AnyAddress4");
        Order order5 = new Order("Order5", LocalDateTime.now().minusDays(5), "AnyAddress5");

        order1 = orderRepository.save(order1);
        order2 = orderRepository.save(order2);
        order3 = orderRepository.save(order3);
        order4 = orderRepository.save(order4);
        order5 = orderRepository.save(order5);

        OrderLine orderLine1 = new OrderLine(goods1.getId(), order5.getId(), 10);
        OrderLine orderLine2 = new OrderLine(goods2.getId(), order4.getId(), 5);
        OrderLine orderLine3 = new OrderLine(goods3.getId(), order3.getId(), 3);
        OrderLine orderLine4 = new OrderLine(goods4.getId(), order2.getId(), 20);
        OrderLine orderLine5 = new OrderLine(goods5.getId(), order1.getId(), 6);

        orderLineRepository.save(orderLine1);
        orderLineRepository.save(orderLine2);
        orderLineRepository.save(orderLine3);
        orderLineRepository.save(orderLine4);
        orderLineRepository.save(orderLine5);

    }

}
