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
public class BillForPrint {
    private int billForPrintId;
    private int priceOfBill;
    private String paymentMethod;
    private String nameOfProduct;
    private String categoryOfProduct;
    private int quantityOfProduct;
    private String dateOfPurchase;

    public BillForPrint() {
    }

    public BillForPrint(int billForPrintId, int priceOfBill, String paymentMethod, String nameOfProduct, String categoryOfProduct, int quantityOfProduct, String dateOfPurchase) {
        this.billForPrintId = billForPrintId;
        this.priceOfBill = priceOfBill;
        this.paymentMethod = paymentMethod;
        this.nameOfProduct = nameOfProduct;
        this.categoryOfProduct = categoryOfProduct;
        this.quantityOfProduct = quantityOfProduct;
        this.dateOfPurchase = dateOfPurchase;
    }

    public int getBillForPrintId() {
        return billForPrintId;
    }

    public void setBillForPrintId(int billForPrintId) {
        this.billForPrintId = billForPrintId;
    }

    public int getPriceOfBill() {
        return priceOfBill;
    }

    public void setPriceOfBill(int priceOfBill) {
        this.priceOfBill = priceOfBill;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getCategoryOfProduct() {
        return categoryOfProduct;
    }

    public void setCategoryOfProduct(String categoryOfProduct) {
        this.categoryOfProduct = categoryOfProduct;
    }

    public int getQuantityOfProduct() {
        return quantityOfProduct;
    }

    public void setQuantityOfProduct(int quantityOfProduct) {
        this.quantityOfProduct = quantityOfProduct;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    
    
    
    
}
