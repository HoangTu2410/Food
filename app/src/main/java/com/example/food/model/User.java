package com.example.food.model;

import java.io.Serializable;

public class User implements Serializable {
    public static final int CUSTOMER = 0;
    public static final int ADMIN = 1;
    private int id;
    private String name, birthday, address, username, password;
    private int position;

    public User() {
    }

    public User(String name, String birthday, String address, String username, String password, int position) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public User(int id, String name, String birthday, String address, String username, String password, int position) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.username = username;
        this.password = password;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
