package com.company;

public class Payment {
    private Money amount;

    //Payment is a type of money exchange between customer and store
    public Payment(Money cashTendered) {

        amount = cashTendered;
    }
    public void setAmount(Money amount){
        this.amount = amount;
    } // unnecessary
    public Money getAmount() { // unnecessary

        return amount;
    }
}
