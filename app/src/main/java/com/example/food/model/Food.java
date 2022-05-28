package com.example.food.model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id, img;
    private String name,description;
    private float price;
    private Category category;

    public Food() {
    }

    public Food(int img, String name, String description, float price, Category category) {
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Food(int id, int img, String name, String description, float price, Category category) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
