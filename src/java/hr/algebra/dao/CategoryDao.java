/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class CategoryDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public CategoryDao(Connection con) {
		super();
		this.con = con;
	}
    
    
    public int addCategory(Category category) throws SQLException{
            
                DataSource ds = DataSourceSingleton.getInstance();
                Connection con = ds.getConnection();
            
            int status = 0;
            try{
                //query = "INSERT INTO Proizvod set IDProizvod=?, Naziv=?, KategorijaID=?, Boja=?, Cijena=?, Kolicina=?, Kolicina=?, Opis=?";
                query = "INSERT INTO Kategorija (Naziv) VALUES (?)";
                pst = this.con.prepareStatement(query);
                pst.setString(1, category.getCategoryName());
                status = pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            return status;
    }
    
    public void deleteCategory(int id) throws SQLException{
        //Product product = null;
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            try{
                query = "delete from Kategorija where IDKategorija=?";
                
                pst = this.con.prepareStatement(query);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            
            
    }
    
    
    public int editCategory(Category category) throws SQLException{
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            int i = 0;
            try{
                query = "update Kategorija set Naziv=? where IDKategorija=?";
                pst = this.con.prepareStatement(query);
                
                pst.setString(1, category.getCategoryName());
                pst.setInt(2, category.getId());
                i = pst.executeUpdate();
                
                
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            
            return i;
    }
    
    
}
