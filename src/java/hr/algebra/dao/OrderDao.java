/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class OrderDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public OrderDao(Connection con) {
		super();
		this.con = con;
	}
    
    
    public boolean insertOrder(Order model){
        
        boolean result = false;
        try{
            query = "insert into Orders (ProductID, UserID, RacunID, Kolicina, Cijena, Datum) values(?,?,?,?,?,?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, model.getId());
            pst.setInt(2, model.getUserId());
            pst.setInt(3, model.getBillId());
            pst.setInt(4, model.getQunatity());
            pst.setDouble(5,model.getPrice());
            pst.setString(6, model.getDate());
            pst.executeUpdate();
            result=true;
            
            
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        
        return result;
    }
    
    
    public List<Order> getAllOrders() {
        List<Order> orders;
            orders = new ArrayList<>();
        try {

            query = "select * from Orders";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Order row = new Order();
                row.setOrderId(rs.getInt("IDOrder"));
                row.setId(rs.getInt("ProductID"));
                row.setUserId(rs.getInt("UserID"));
                row.setBillId(rs.getInt("RacunID"));
                row.setQunatity(rs.getInt("Kolicina"));
                row.setPrice(rs.getInt("Cijena"));
                row.setDate(rs.getString("Datum"));

                orders.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    public List<Order> getOrdersForID(int id) {
        List<Order> orders;
            orders = new ArrayList<>();
        try {

            query = "select * from Orders where RacunID = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
            	Order row = new Order();
                row.setOrderId(rs.getInt("IDOrder"));
                row.setId(rs.getInt("ProductID"));
                row.setUserId(rs.getInt("UserID"));
                row.setBillId(rs.getInt("RacunID"));
                row.setQunatity(rs.getInt("Kolicina"));
                row.setDate(rs.getString("Datum"));

                orders.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return orders;
    }
    
    public void deleteAllOrders() throws SQLException{
        //Product product = null;
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            try{
                query = "delete from Orders";
                
                pst = this.con.prepareStatement(query);
                pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            
            
    }
    
}
