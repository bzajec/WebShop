/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.LoginInf;
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
public class LoginInfDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public LoginInfDao(Connection con) {
        this.con = con;
    }
    
    public int addLog(LoginInf log) throws SQLException{
            
                DataSource ds = DataSourceSingleton.getInstance();
                Connection con = ds.getConnection();
            
            int status = 0;
            try{
                query = "INSERT INTO Loginovi (Ime, Prezime, Vrijeme, IPAdresa) VALUES (?,?,?,?)";
                pst = this.con.prepareStatement(query);
                pst.setString(1, log.getFirstName());
                pst.setString(2, log.getLastName());
                pst.setString(3, log.getDateTime());
                pst.setString(4, log.getIpAdress());
                status = pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            return status;
    }
    
    public List<LoginInf> getAllLoginInf() {
        List<LoginInf> login;
            login = new ArrayList<>();
        try {

            query = "select * from Loginovi";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
            	LoginInf row = new LoginInf();
                row.setId(rs.getInt("IDLogin"));
                row.setFirstName(rs.getString("Ime"));
                row.setLastName(rs.getString("Prezime"));
                row.setDateTime(rs.getString("Vrijeme"));
                row.setIpAdress(rs.getString("IPAdresa"));

                login.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return login;
    }
}
