package com.example.zdarzenia_w_springu_1_2.model;

import com.example.zdarzenia_w_springu_1_2.model.annotations.Convertable;

import java.io.Serializable;

@Convertable
public class OrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String product;
    private int cost;
    private int quantity;

    public OrderDTO(String product, int cost, int quantity) {
        this.product = product;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public int getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "product='" + product + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                '}';
    }
}
