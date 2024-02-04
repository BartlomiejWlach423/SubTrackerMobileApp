package com.example.subtracker;

public class ListData {
    String name;
    int id, payment;
    float cost;


    public ListData(String name, float cost, int payment, int id){
        this.name = name;
        this.cost = cost;
        this.payment = payment;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPayment() {
        return payment;
    }

    public float getCost() {
        return cost;
    }
}
