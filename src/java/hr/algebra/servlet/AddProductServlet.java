/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.dao.ProductDao;
import hr.algebra.model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class AddProductServlet extends HttpServlet {
    
    static public int productId;
    static public String name;
    static public int categoryId;
    static public double price;
    static public String image;
    static public int amount;
    static public String description;
    static public String color;
    
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        
        
        
        
        try {
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            
            
            PrintWriter out = response.getWriter();
            Product product = new Product();
            ProductDao dao = new ProductDao(con);
            
            
            product.setId(Integer.parseInt(request.getParameter("id")));
            product.setProductName((request.getParameter("product_name")));
            product.setCategoryID(Integer.parseInt(request.getParameter("product_category")));
            product.setColor((request.getParameter("product_color")));
            product.setPrice(Integer.parseInt(request.getParameter("product_price")));
            product.setAmount(Integer.parseInt(request.getParameter("product_amount")));
            product.setImage((request.getParameter("product_picture")));
            product.setDescription((request.getParameter("product_description")));
            
            
            dao.addProduct(product);
            
            response.sendRedirect("productsAdmin.jsp");  
            
            //response.sendRedirect("productsAdmin.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
