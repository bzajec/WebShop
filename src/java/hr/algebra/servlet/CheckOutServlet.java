/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.dao.BillDao;
import hr.algebra.dao.OrderDao;
import hr.algebra.dao.ProductDao;
import hr.algebra.model.Bill;
import hr.algebra.model.Cart;
import hr.algebra.model.Order;
import hr.algebra.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
        
        
        
        try(PrintWriter out = response.getWriter()){
            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
            Date date = new Date();
            
            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth");
            if(cart_list != null && auth!=null){
                Bill bill = new Bill();
                bill.setUserId(auth.getId());
                BillDao bD = new BillDao(con);
                boolean billResult = bD.insertBill(bill);
                int billID = bD.getBillID(bill.getUserId());
                
                
                for(Cart c:cart_list){
                    Order order = new Order();
                    order.setId(c.getId());
                    order.setUserId(auth.getId());
                    order.setBillId(billID);
                    order.setQunatity(c.getQuantity());
                    order.setPrice(c.getPrice());
                    order.setDate(formatter.format(date));
                    
                    
                    
                    
                    OrderDao dao = new OrderDao(con);
                    ProductDao productDao = new ProductDao(con);
                    productDao.lowerAmount(c.getId(), c.getQuantity());
                    boolean result = dao.insertOrder(order);
                    if(!result) break;
                }
                cart_list.clear();
                response.sendRedirect("paymentMethodPouzece.jsp");
                
            }else{
                if(auth == null) response.sendRedirect("login.jsp");
                response.sendRedirect("cart.jsp");
            }
            
            
            
        } catch(Exception e){
                e.printStackTrace();
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
