/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.model.FinalStavka;
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
public class FinalOrderDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public FinalOrderDao(Connection con) {
		super();
		this.con = con;
	}
    
    public boolean insertFinalOrder(FinalStavka model){
        
        boolean result = false;
        try{
            query = "insert into FinalOrder (IDOrder, ImeProizvoda, RacunID, Kolicina, Cijena) values(?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getStavkaID());
            pst.setString(2, model.getProductName());
            pst.setInt(3, model.getFinalBillID());
            pst.setInt(4, model.getQuantity());
            pst.setInt(5, model.getTotalPrice());
            pst.executeUpdate();
            result=true;
            
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        return result;
    }
    
    public List<FinalStavka> getAllFinalOrders() {
        List<FinalStavka> orders;
            orders = new ArrayList<>();
        try {

            query = "select * from FinalOrder";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	FinalStavka row = new FinalStavka();
                row.setStavkaID(rs.getInt("IDOrder"));
                row.setProductName(rs.getString("ImeProizvoda"));
                row.setFinalBillID(rs.getInt("RacunID"));
                row.setQuantity(rs.getInt("Kolicina"));
                row.setTotalPrice(rs.getInt("Cijena"));
                

                orders.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    
}
