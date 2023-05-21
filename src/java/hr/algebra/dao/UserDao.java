/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class UserDao {

    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public UserDao(Connection con) {
        this.con = con;
    }

    public User userLogin(String email, String password) {
        User user = null;
        try {
            query = "select * from Korisnik where Email=? and Pass=?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("IDKoristnik"));
                user.setFirstName(rs.getString("Ime"));
                user.setLastName(rs.getString("Prezime"));
                user.setEmail(rs.getString("Email"));
                user.setAdmin(rs.getBoolean("Administrator"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
    
    public int addUser(User user) throws SQLException{
            
                DataSource ds = DataSourceSingleton.getInstance();
                Connection con = ds.getConnection();
            
            int status = 0;
            try{
                query = "INSERT INTO Korisnik (Ime, Prezime, Email, Pass, Administrator) VALUES (?,?,?,?,?)";
                pst = this.con.prepareStatement(query);
                pst.setString(1, user.getFirstName());
                pst.setString(2, user.getLastName());
                pst.setString(3, user.getEmail());
                pst.setString(4, user.getPassword());
                pst.setBoolean(5, user.isAdmin());
                status = pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            return status;
    }
    
    
}
