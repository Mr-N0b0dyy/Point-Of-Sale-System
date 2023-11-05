package com.company;


public class SalesLineItem {
    private int quantity;
    private ProductDescription product;

    public SalesLineItem(ProductDescription product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDescription getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        // Calculate the subtotal by multiplying the product price with the quantity
        return product.getPrice().multiply(quantity);
    }

    @Override
    public String toString() {
        String str = product.toString() + " ";
        String str1 = String.format("%1$" + 3 + "s", quantity);
        String str2 = String.format("%1$" + 5 + "s", " x ");
        String str3 = String.format("%1$" + 5 + "s", product.getPrice().getValue());

        return str + str1 + str2 + str3 + " = " + getSubtotal() + "$";
    }
}