package com.example.springbootgradleapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_line")
public class OrderLine {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_line_id")
    private Long id;
    @Column(name = "order_id")
    @JoinTable(name = "orders",joinColumns = {@JoinColumn(name = "order_id")})
    private Long orderId;
    @Column(name = "goods_id")
    @JoinTable(name = "goods",joinColumns = {@JoinColumn(name = "goods_id")})
    private Long goodsId;
    @Column(name = "order_line_count")
    private Integer count;

    public OrderLine() {
    }

    public OrderLine(Long orderId, Long goodsId, Integer count) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.count = count;
    }

    public OrderLine(Long id, Long orderId, Long goodsId, Integer count) {
        this.id = id;
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine line = (OrderLine) o;
        return orderId.equals(line.orderId) &&
                goodsId.equals(line.goodsId) &&
                count.equals(line.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, goodsId, count);
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", goodsId=" + goodsId +
                ", count=" + count +
                '}';
    }
}
