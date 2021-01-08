package com.example.springbootgradleapp.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "order_name")
    private String name;
    @Column(name = "order_date")
    private LocalDateTime date;
    @Column(name = "order_address")
    private String address;

    public Order() {
    }

    public Order(String name) {
        this.name = name;
    }

    public Order(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Order(String name, LocalDateTime date, String address) {
        this.name = name;
        this.date = date;
        this.address = address;
    }

    public Order(Long id, String name, LocalDateTime date, String address) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(name, order.name) &&
                Objects.equals(date, order.date) &&
                Objects.equals(address, order.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, date, address);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", address='" + address + '\'' +
                '}';
    }
}
