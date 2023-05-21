/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Bruno
 */
public class BillDao {
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public BillDao(Connection con) {
		super();
		this.con = con;
	}
    
    
    public boolean insertBill(Bill model){
        
        boolean result = false;
        try{
            query = "insert into Racun (UserID) values(?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getUserId());
            pst.executeUpdate();
            result=true;
            
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        return result;
    }
    public int getBillID (int id) throws SQLException{
        int i = 0;
        Bill bill = new Bill();
        try{
            query ="select * from Racun where UserID=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
            bill.setBillId(rs.getInt("IDRacun"));
            bill.setUserId(rs.getInt("UserID"));
            }
            
            
    }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bill.getBillId();
    }
    
    
    
    
    public Bill getLastBill () throws SQLException{
        int i = 0;
        Bill bill = new Bill();
        try{
            query ="select top 1 * from Racun ORDER BY IDRacun DESC";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
            bill.setBillId(rs.getInt("IDRacun"));
            bill.setUserId(rs.getInt("UserID"));
            }
            
            
    }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bill;
    }
}
    
    
    

