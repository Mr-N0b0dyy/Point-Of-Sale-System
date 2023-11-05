package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class Register {

    private Map<String, ProductDescription> productCatalog;
    private Map<String, Sale> sales;
    private Sale currentSale;
    private Return currentReturn;
    // Constructor to initialize the productCatalog and sales maps
    public Register(Map<String, ProductDescription> productCatalog, Map<String, Sale> sales) {
        this.sales = sales;
        this.productCatalog = productCatalog;
    }
    // Set the current sale based on the provided sale ID


    public void setCurrent(String id) {
        currentSale = sales.get(id);
    }
    // Get the current sale
    public Sale getCurrentSale() {
        return this.currentSale;
    }
    // Get the current return
    public Return getReturn() {
        return this.currentReturn;
    }
    // Create a new return for the current sale
    public void makeNewReturn() {
        currentReturn = currentSale.createRet();
    }
    // Create a new sale with a unique key
    public void makeNewSale() { // unnecessary
        // Generate a unique key for the new sale
        String uniqueKey;
        do {
            uniqueKey = generateUniqueKey();
        } while (sales.containsKey(uniqueKey));

        // Create a new Sale object with the unique key
        currentSale = new Sale(uniqueKey);
    }
    // Generate a unique key for sales
    private String generateUniqueKey() {
        // Implement your own logic to generate a unique key
        // For example, you can use a combination of timestamp and a random number
        String timestamp = Long.toString(System.currentTimeMillis());
        String random = Integer.toString((int) (Math.random() * 1000));
        return timestamp + "-" + random;
    }
    // Check if the return is valid and allowed
    public String checkForReturn() {
        boolean allowed = true;
        for (SalesLineItem item : currentSale.getLineItems()) {
            for (SalesLineItem item2 : currentReturn.getLineItems()) {
                if (item.getProduct().getItemID() == item2.getProduct().getItemID()) {
                    if (item.getQuantity() > item2.getQuantity()) {
                        allowed = false;
                        return "Customer trying to return more items than sold to them";
                    }
                } else {
                    allowed = false;
                    return "Returning item: " + item2.getProduct().getItemID() + " " + item2.getProduct().getDescription();
                }
            }
        }
        currentReturn.setAllowed(allowed);
        return "Return process is allowed";
    }
    // Add an item and its quantity to the sale or return
    public void enterItem(String id, int count, String type) {
        // Find the product by item identifier
        ProductDescription product = productCatalog.get(id);

        // Add the item and the quantity to the sale or return based on the type
        if (Objects.equals(type, "1")) { // unnecessary
            currentSale.makeLineItem(product, count);
        } else {
            currentReturn.makeLineItem(product, count);
        }
    }
    //Added so in continuous usage of register it stays updated
    public void updateOrAddSale(Sale sale) {
        sales.put(sale.getSaleId(), sale);
    }
    // Make a payment for the sale or return
    public String makePayment(Money cashTendered, String type) {
        // Pay the sale or return with the money given by the customer
        if (Objects.equals(type, "1")) { // unnecessary
            return currentSale.makePayment(cashTendered);
        } else {
            return currentReturn.makePayment(cashTendered);
        }
    }
    // End the current sale
    public void endSale() { // unnecessary
        // End the sale once all items are entered
        currentSale.becomeComplete();
    }
    // End the current return
    public void endReturn() {
        // End the return once all items are entered
        currentReturn.becomeComplete();
    }
}





