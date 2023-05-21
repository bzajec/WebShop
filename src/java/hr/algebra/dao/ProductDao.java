/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.model.Cart;
import hr.algebra.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class ProductDao {
    
    private Connection con;
    private String query;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public ProductDao(Connection con) {
		super();
		this.con = con;
	}
    
    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> cartProduct = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select * from Proizvod where IDProizvod=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setId(rs.getInt("IDProizvod"));
                        row.setProductName(rs.getString("Naziv"));
                        row.setCategoryID(rs.getInt("KategorijaID"));
                        row.setColor(rs.getString("Boja"));
                        row.setPrice(rs.getInt("Cijena")*item.getQuantity());
                        row.setAmount(rs.getInt("Kolicina"));
                        row.setImage(rs.getString("Slika"));
                        row.setDescription(rs.getString("Opis"));
                        row.setQuantity(item.getQuantity());
                        cartProduct.add(row);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return cartProduct;
    }
    
    
    
    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sumaPrice = 0;
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    query = "select Cijena from Proizvod where IDProizvod=?";
                    pst = this.con.prepareStatement(query);
                    pst.setInt(1, item.getId());
                    rs = pst.executeQuery();
                    while (rs.next()) {
                        sumaPrice+=rs.getDouble("Cijena")*item.getQuantity();
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return sumaPrice;
    }
    
    
    public Product getSingleProduct(int id) throws SQLException {
        
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            
            Product product = new Product();
        
            try{
                query = "select * from Proizvod where IDProizvod=?";
                pst = this.con.prepareStatement(query);
                rs = pst.executeQuery();
                
                if(rs.next()){
                    product.setProductName(rs.getString("Naziv"));
                    product.setCategoryID(rs.getInt("KategorijaID"));
                    product.setColor(rs.getString("Boja"));
                    product.setPrice(rs.getInt("Cijena"));
                    product.setAmount(rs.getInt("Kolicina"));
                    product.setImage(rs.getString("Slika"));
                    product.setDescription(rs.getString("Opis"));
                    
                }
                
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            
            return product;
		 
    }
    
    public void deleteProduct(int id) throws SQLException{
        //Product product = null;
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            try{
                query = "delete from Proizvod where IDProizvod=?";
                
                pst = this.con.prepareStatement(query);
                pst.setInt(1, id);
                pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            
            
    }
    
    public int addProduct(Product product) throws SQLException{
            
                DataSource ds = DataSourceSingleton.getInstance();
                Connection con = ds.getConnection();
            
            int status = 0;
            try{
                
                
                query = "INSERT INTO Proizvod (IDProizvod, Naziv, KategorijaID, Boja, Cijena, Kolicina, Slika, Opis) VALUES (?,?,?,?,?,?,?,?)";
                pst = this.con.prepareStatement(query);
                
                pst.setInt(1, product.getId());
                pst.setString(2, product.getProductName());
                pst.setInt(3, product.getCategoryID());
                pst.setString(4, product.getColor());
                pst.setDouble(5, product.getPrice());
                pst.setInt(6, product.getAmount());
                pst.setString(7, product.getImage());
                pst.setString(8, product.getDescription());
                status = pst.executeUpdate();
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            return status;
    }
    
    public int editProduct(Product product) throws SQLException{
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            int i = 0;
            try{
                query = "update Proizvod set Naziv=?, KategorijaID=?, Boja=?, Cijena=?, Kolicina=?, Slika=?, Opis=? where IDProizvod=?";
                pst = this.con.prepareStatement(query);
                
                pst.setString(1, product.getProductName());
                pst.setInt(2, product.getCategoryID());
                pst.setString(3, product.getColor());
                pst.setDouble(4, product.getPrice());
                pst.setInt(5, product.getAmount());
                pst.setString(6, product.getImage());
                pst.setString(7, product.getDescription());
                pst.setInt(8, product.getId());
                i = pst.executeUpdate();
                
                
            } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }
            
            return i;
    }
    
   public String getProductName (int id) throws SQLException{
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            int i = 0;
            Product product = new Product();
            try{
                query = "select * from Proizvod where IDProizvod=?";
                pst = this.con.prepareStatement(query);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                while(rs.next()){
                product.setId(rs.getInt("IDProizvod"));
                product.setProductName(rs.getString("Naziv"));
                product.setCategoryID(rs.getInt("KategorijaID"));
                product.setColor(rs.getString("Boja"));
                product.setPrice(rs.getInt("Cijena"));
                product.setAmount(rs.getInt("Kolicina"));
                product.setImage(rs.getString("Slika"));
                product.setDescription(rs.getString("Opis"));
                }
            } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       return product.getProductName();
   }
   
   public int getPriceForProduct (int id) throws SQLException{
        int i = 0;
        Product product = new Product();
        try{
            query ="select * from Proizvod where IDProizvod=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
            product.setId(rs.getInt("IDProizvod"));
            product.setProductName(rs.getString("Naziv"));
            product.setCategoryID(rs.getInt("KategorijaID"));
            product.setColor(rs.getString("Boja"));
            product.setPrice(rs.getInt("Cijena"));
            product.setAmount(rs.getInt("Kolicina"));
            product.setImage(rs.getString("Slika"));
            product.setDescription(rs.getString("Opis"));
            }
            
            
    }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return product.getPrice();
    }
   
   public void lowerAmount (int id, int quantity){
       
       try{
            query ="update Proizvod set Kolicina=Kolicina-? where IDProizvod=?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, quantity);
            pst.setInt(2,id);
            rs = pst.executeQuery();
            
       
       
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
}}
