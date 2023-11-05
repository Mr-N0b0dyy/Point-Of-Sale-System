package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Return {
    private List<SalesLineItem> lineItems;
    private DateTimeFormatter dtf;
    private Payment payment;
    private Money total;
    private boolean allowed;

    private String DateTime;
    public Return(){

        lineItems = new ArrayList<SalesLineItem>();
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        //Initialize empty money object to hold the total of the return
        total = new Money();
    }
    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }
    public List<SalesLineItem> getLineItems() {
        return lineItems;
    }
    public String getDateTime() {
        return DateTime;
    }
    public Money getTotal() {
        return total;
    }

    public Payment getPayment() {// unnecessary
        return payment;
    }
    public void becomeComplete() {
        //Calculate the total amount when the return is complete
        DateTime = dtf.format(LocalDateTime.now());
        calculateTotal();
    }
    public void makeLineItem(ProductDescription product, int quantity) {
        //Add new item and its quantity to the return
        lineItems.add(new SalesLineItem(product, quantity));
    }
    public Double calculateTotal() {
        Double subtotal;
        //Find the subtotal of each SaleLineItem
        for (SalesLineItem lineItem : lineItems) {
            //Multiplied by the quantity and product's price
            subtotal = lineItem.getSubtotal();
            total.add(subtotal);
        }
        return total.getValue();
    }
    public String makePayment(Money cashTendered) {
        //When the return is complete , customer pays the amount
        payment = new Payment(cashTendered);
        //After payment is received, the return is recorded to a text file
        return this.toString();
    }

    @Override
    public String toString() {
        String receipt = "\n ➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤ \n\n";
        //Details of the return
        for (SalesLineItem item : lineItems) {
            receipt += "✯ " + item.toString() + "\n";
        }
        receipt += "✎ Total: " + Double.toString(total.getValue()) + " $ \n";
        //Timestamp of the sale
        receipt += "⌛ " + DateTime + "\n";
        return receipt;

    }
}
