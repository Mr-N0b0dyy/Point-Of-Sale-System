package com.company;
public class Money {
    private double value;

    //Initialize zero valued money
    public Money(){
        value = 0.0;
    }
    //Initialize money with the given amount
    public Money(double value){
        this.value = value;
    }
    public double getValue(){
        return value;
    }
    public void setValue(double value){
        this.value = value;
    }
    //Add some double amount to the money
    public void add(double amount){
        value += amount;
    }
    //Multiply the money with the given quantity variable
    public double multiply(int quantity) {

        return value * quantity;
    }
}
