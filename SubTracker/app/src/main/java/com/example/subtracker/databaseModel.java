package com.example.subtracker;

public class databaseModel {
    private String subscryptionName;
    private Float cost;
    private int paymentDay;
    private int id;

    public databaseModel(String subscryptionName, Float cost, int paymentDay, int id) {
        this.subscryptionName = subscryptionName;
        this.cost = cost;
        this.paymentDay = paymentDay;
        this.id = id;
    }

    public databaseModel() {
    }

    @Override
    public String toString() {
        return id + " " + subscryptionName + " " + cost + " " + paymentDay;
    }

    public String getSubscryptionName() {
        return subscryptionName;
    }

    public void setSubscryptionName(String subscryptionName) {
        this.subscryptionName = subscryptionName;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public int getPaymentDay() {
        return paymentDay;
    }

    public void setPaymentDay(int paymentDay) {
        this.paymentDay = paymentDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
