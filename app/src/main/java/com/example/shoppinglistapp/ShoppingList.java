package com.example.shoppinglistapp;

public class ShoppingList {
    String name;
    String size;
    int price;



    public ShoppingList (String name, String size, int price){
        this.name = name;
        this.size = size;
        this.price = price;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getSize() {return size;}

    public void setSize(String size) {this.size = size;}

    public double getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}


}
