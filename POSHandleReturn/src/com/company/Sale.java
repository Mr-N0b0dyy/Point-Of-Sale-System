package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sale {
    private String saleId;
    private List<SalesLineItem> lineItems;

    private List<Return> Returns; // Consider renaming this field to "returns" (lowercase "r") for naming consistency

    private DateTimeFormatter dtf;

    private Payment payment;
    private Money total;

    private String DateTime; // Consider renaming this field to "dateTime" (lowercase "d") for naming consistency

    public Sale(String saleID){
        //Initialize a list to hold the items of the sale
        this.saleId = saleID;
        lineItems = new ArrayList<SalesLineItem>();
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        //Initialize empty money object to Hold the total of the sale
        total = new Money();

    }
    public Money getTotal() { // unnecessary
        return total;
    }
    public String getSaleId() {
        return saleId;
    }
    public Payment getPayment() { // unnecessary
        return payment;
    }
    public List<SalesLineItem> getLineItems() {
        return lineItems;
    }
    public String getDateTime() { // unnecessary
        return DateTime;
    }
    public List<Return> getReturns() { // unnecessary
        return Returns;
    }
    public void becomeComplete() {
        //Calculate the total amount when the sale is complete
        DateTime = dtf.format(LocalDateTime.now());
        calculateTotal();
    }
    public void makeLineItem(ProductDescription product, int quantity) {
        //Add new item and its quantity to the sale
        lineItems.add(new SalesLineItem(product, quantity));
    }

    public void saveReturn(Return rEturn){
        Returns.add(rEturn);
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
        //when the sale is complete , customer pays the amount
        payment = new Payment(cashTendered);
        //After payment is received, the sale is recorded to a text file
        return this.toString();
    }
    public Return createRet(){
        return new Return();
    }

    @Override
    public String toString() {
        String receipt = "\n ➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤➤ \n\n";
        receipt += "Sale Id" + saleId +"\n";
        //Details of the sale
        for (SalesLineItem item : lineItems) {
            receipt += "✯ " + item.toString() + "\n";
        }
        receipt += "✎ Total: " + Double.toString(total.getValue()) + " $ \n";
        //Timestamp of the sale
        receipt += "⌛ " + DateTime + "\n";
        return receipt;

    }

}
