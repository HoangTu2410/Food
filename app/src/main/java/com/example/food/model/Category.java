package com.example.food.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int id, img;
    private String name;

    public Category() {
    }

    public Category(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public Category(int id, int img, String name) {
        this.id = id;
        this.img = img;
        this.name = name;
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
}
