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
public class LoginInf {
    
    private int id;
    private String firstName;
    private String lastName;
    private String dateTime;
    private String ipAdress;

    public LoginInf(int id, String firstName, String lastName, String dateTime, String ipAdress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateTime = dateTime;
        this.ipAdress = ipAdress;
    }

    public LoginInf() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }
    
    
    
}
