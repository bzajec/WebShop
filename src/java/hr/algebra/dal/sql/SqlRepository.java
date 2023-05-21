/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Cart;
import hr.algebra.model.Category;
import hr.algebra.model.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
/**
 *
 * @author Bruno
 */
public class SqlRepository implements Repository {
    
    private static final String ID_PRODUCT = "IDProizvod";
    private static final String NAME = "Naziv";
    private static final String CATEGORY_ID = "KategorijaID";
    private static final String COLOR = "Boja";
    private static final String PRICE = "Cijena";
    private static final String AMOUNT = "Kolicina";
    private static final String PICTURE = "Slika";
    private static final String DESCRIPTION = "Opis";
    
    private static final String ID_CATEGORY = "IDKategorija";
    private static final String CATEGORY_NAME = "Naziv";
    
    
    private static final String SELECT_PRODUCT = "{ CALL selectProizvod (?) }";
    private static final String SELECT_PRODUCTS = "{ CALL getProizvod }";
    //private static final String GET_PRODUCT_FOR_CART = " CALL getProizvodZaKorsaricu (?) }";
    private static final String CREATE_STUDENT = "{ CALL createStudent  (?,?,?,?,?) }";
    private static final String ADD_PRODUCT ="{ CALL addProizvod (?,?,?,?,?,?,?,?) }" ;
    private static final String SELECT_CATEGORIES =" { CALL getKategorije }";
    
    
    
    
    @Override
      public List<Product> selectProizvodi() throws Exception {
          List<Product> products = new ArrayList<>();
          DataSource dataSource = DataSourceSingleton.getInstance();
          try (Connection con = dataSource.getConnection();
                  CallableStatement stmt = con.prepareCall(SELECT_PRODUCTS);
                  ResultSet rs = stmt.executeQuery()){
                      while (rs.next()) {
                          products.add(new Product(
                                    rs.getInt(ID_PRODUCT), 
                                    rs.getString(NAME),
                                    rs.getInt(CATEGORY_ID),
                                    rs.getString(COLOR),
                                    rs.getInt(PRICE),
                                    rs.getInt(AMOUNT),
                                    rs.getString(PICTURE),
                                    rs.getString(DESCRIPTION)));
                      }
          }
          return products;    
      }
      
      
      @Override
      public List<Category> selectKategorije() throws Exception {
          List<Category> categories = new ArrayList<>();
          DataSource dataSource = DataSourceSingleton.getInstance();
          try (Connection con = dataSource.getConnection();
                  CallableStatement stmt = con.prepareCall(SELECT_CATEGORIES);
                  ResultSet rs = stmt.executeQuery()){
                      while (rs.next()) {
                          categories.add(new Category(
                                    rs.getInt(ID_CATEGORY), 
                                    rs.getString(CATEGORY_NAME)));
                      }
          }
          return categories;    
      }
      
      
    
      
}
