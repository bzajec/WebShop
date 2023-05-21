/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author Bruno
 */
public class FinalStavka {
    
    private int stavkaID;
    private String productName;
    private int quantity;
    private int totalPrice;
    private int finalBillID;

    public FinalStavka() {
    }

    public FinalStavka(int stavkaID, String productName, int quantity, int totalPrice, int finalBillID) {
        this.stavkaID = stavkaID;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.finalBillID = finalBillID;
    }

    public int getStavkaID() {
        return stavkaID;
    }

    public void setStavkaID(int stavkaID) {
        this.stavkaID = stavkaID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getFinalBillID() {
        return finalBillID;
    }

    public void setFinalBillID(int finalBillID) {
        this.finalBillID = finalBillID;
    }
    
    
    
}
