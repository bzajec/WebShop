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
public class FinalBill {
    
    private int billID;
    private int userID;
    private String customerFirstName;
    private String customerLastName;
    private String typeOfPayment;
    private String dateOfPurchase;

    public FinalBill() {
    }

    public FinalBill(int billID, int userID, String customerFirstName, String customerLastName, String typeOfPayment, String dateOfPurchase) {
        this.billID = billID;
        this.userID = userID;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.typeOfPayment = typeOfPayment;
        this.dateOfPurchase = dateOfPurchase;
    }

    

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getTypeOfPayment() {
        return typeOfPayment;
    }

    public void setTypeOfPayment(String typeOfPayment) {
        this.typeOfPayment = typeOfPayment;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
    
    
    
}
