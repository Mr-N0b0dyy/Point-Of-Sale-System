package com.company;

public class ProductDescription {

    private String id;
    private Money money;
    private String description;

    // Represents individual products with their information
    public ProductDescription(String id, Money money, String description) {
        this.id = id;
        this.money = money;
        this.description = description;
    }

    public String getItemID() {
        return id;
    }

    // Set the price of the product
    public void setPrice(Double price){ // unnecessary
        money.setValue(price);
    }

    public Money getPrice() {
        return money;
    }

    public String getDescription() {
        return description;
    }

    // Override the toString() method to provide a custom string representation of the object
    @Override
    public String toString(){
        return String.format("%1$"+14+ "s", getDescription()+" → ");
    }
}

