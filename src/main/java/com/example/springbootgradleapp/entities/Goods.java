package com.example.springbootgradleapp.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "goods_id")
    private Long id;
    @Column(name = "goods_name")
    private String name;
    @Column(name = "goods_price")
    private Integer price;

    public Goods() {
    }

    public Goods(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public Goods(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return name.equals(goods.name) &&
                price.equals(goods.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
