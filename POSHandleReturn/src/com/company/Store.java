package com.company;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private String address;
    private String name;
    private Map<String, Sale > completedSales;
    private Register reg;
    private Map<String, ProductDescription> productCatalog;
    public Store(String name, String address){
        productCatalog = new HashMap<String, ProductDescription>();
        this.name = name;
        this.address = address;
    }
    public Register getReg() {
        return reg;
    }
    public void updateOrAddSale(Sale sale) {
        completedSales.put(sale.getSaleId(), sale);
    }
    // The method below is added so code could run without a database
    public void addProduct(String productId, ProductDescription product) { // unnecessary
        productCatalog.put(productId, product);
    }
    public Register create(){ // unnecessary
        reg = new Register(productCatalog, completedSales);
        return reg;
    }
}
