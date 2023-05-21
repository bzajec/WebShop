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
public class Order extends Product {
    
        private int orderId;
	private int userId;
        private int billId;
	private int qunatity;
	private String date;

    public Order() {
    }

    public Order(int orderId, int userId, int billId, int qunatity, String date) {
        this.orderId = orderId;
        this.userId = userId;
        this.billId = billId;
        this.qunatity = qunatity;
        this.date = date;
    }

    public Order(int userId, int billId, int qunatity, String date) {
        this.userId = userId;
        this.billId = billId;
        this.qunatity = qunatity;
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getQunatity() {
        return qunatity;
    }

    public void setQunatity(int qunatity) {
        this.qunatity = qunatity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
