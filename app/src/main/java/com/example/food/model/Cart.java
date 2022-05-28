package com.example.food.model;

import java.io.Serializable;

public class Cart implements Serializable {
    private int id;
    private int amount;
    private User customer;
    private Food food;

    public Cart() {
    }

    public Cart(int amount, User customer, Food food) {
        this.amount = amount;
        this.customer = customer;
        this.food = food;
    }

    public Cart(int id, int amount, User customer, Food food) {
        this.id = id;
        this.amount = amount;
        this.customer = customer;
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
