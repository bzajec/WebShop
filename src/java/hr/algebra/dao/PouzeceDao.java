/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Pouzece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class PouzeceDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public PouzeceDao(Connection con) {
		super();
		this.con = con;
	}
    
    
    
    public int addPouzece(Pouzece pouzece) throws SQLException{
            
                DataSource ds = DataSourceSingleton.getInstance();
                Connection con = ds.getConnection();
            
            int status = 0;
            try{
                
                query = "INSERT INTO Pouzece (Adresa, PostanskiBroj, Grad) VALUES (?,?,?)";
                pst = this.con.prepareStatement(query);
                
                pst.setString(1, pouzece.getAdresa());
                pst.setInt(2, pouzece.getPostanskiBroj());
                pst.setString(3, pouzece.getGrad());
                status = pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            return status;
    }
    
}
