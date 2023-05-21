/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.dao.BillDao;
import hr.algebra.dao.CategoryDao;
import hr.algebra.dao.FinalBillDao;
import hr.algebra.dao.FinalOrderDao;
import hr.algebra.dao.OrderDao;
import hr.algebra.dao.ProductDao;
import hr.algebra.model.Bill;
import hr.algebra.model.FinalBill;
import hr.algebra.model.FinalStavka;
import hr.algebra.model.Order;
import hr.algebra.model.Product;
import hr.algebra.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
public class FinalBillServlet extends HttpServlet {

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
            out.println("<title>Servlet FinalBillServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FinalBillServlet at " + request.getContextPath() + "</h1>");
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
        
        
        try{
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            
            PrintWriter out = response.getWriter();
            
            
            Bill bill = new Bill();
            BillDao bD = new BillDao(con);
            bill = bD.getLastBill();
            
            User auth = (User) request.getSession().getAttribute("auth");
            
            FinalBill fBill = new FinalBill();
            fBill.setBillID(bill.getBillId());
            fBill.setCustomerFirstName(auth.getFirstName());
            fBill.setCustomerLastName(auth.getLastName());
            fBill.setUserID(bill.getUserId());
            fBill.setTypeOfPayment("Pouzeće");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            fBill.setDateOfPurchase(formatter.format(date));
            
            FinalBillDao fbD = new FinalBillDao(con);
            fbD.insertFinalBill(fBill);
            
            List<Order> orders = new ArrayList();
            
            OrderDao oD = new OrderDao(con);
            orders = oD.getOrdersForID(bill.getBillId());
            
            Product product = new Product();
            ProductDao productDao = new ProductDao(con);
            
            FinalOrderDao finalOrderDao = new FinalOrderDao(con); 
            
            
            double cijena;
            FinalStavka finalStavka = new FinalStavka();
            for(Order o : orders){
                finalStavka.setStavkaID(o.getOrderId());
                String productName = productDao.getProductName(o.getId());
                finalStavka.setProductName(productName);
                finalStavka.setQuantity(o.getQunatity());
                int price = productDao.getPriceForProduct(o.getId());
                finalStavka.setTotalPrice(o.getQunatity()*price);
                finalStavka.setFinalBillID(o.getBillId());
                
                finalOrderDao.insertFinalOrder(finalStavka);
                
                oD.deleteAllOrders();
                
                
            }
            
            
            response.sendRedirect("products.jsp");
            
        
        } catch (SQLException ex) {
            Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
