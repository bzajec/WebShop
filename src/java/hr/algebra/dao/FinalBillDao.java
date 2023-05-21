/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.model.FinalBill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class FinalBillDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public FinalBillDao(Connection con) {
		super();
		this.con = con;
	}
    
     public boolean insertFinalBill(FinalBill model){
        
        boolean result = false;
        try{
            query = "insert into FinalBill (IDRacun, KupacID, Ime, Prezime, NacinKupnje, DatumKupnje) values(?,?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getBillID());
            pst.setInt(2, model.getUserID());
            pst.setString(3, model.getCustomerFirstName());
            pst.setString(4, model.getCustomerLastName());
            pst.setString(5, model.getTypeOfPayment());
            pst.setString(6, model.getDateOfPurchase());
            
            pst.executeUpdate();
            result=true;
            
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        return result;
    }
    
     public List<FinalBill> getAllFinalBills() {
        List<FinalBill> bills;
            bills = new ArrayList<>();
        try {

            query = "select * from FinalBill";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	FinalBill row = new FinalBill();
                row.setBillID(rs.getInt("IDRacun"));
                row.setUserID(rs.getInt("KupacID"));
                row.setCustomerFirstName(rs.getString("Ime"));
                row.setCustomerLastName(rs.getString("Prezime"));
                row.setTypeOfPayment(rs.getString("NacinKupnje"));
                row.setDateOfPurchase(rs.getString("datumKupnje"));
                

                bills.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return bills;
    }
    
}
