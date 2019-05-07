package com.catalog;

import com.cash.Money;

public class Stuff {
    private String name;
    private String date;
    private double price;
    private Money money;

    public Stuff(String name, String date, double price, Money money) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.money = money;
    }

    public Stuff() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

}
