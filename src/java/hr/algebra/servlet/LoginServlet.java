/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.servlet;

import hr.algebra.dal.sql.DataSourceSingleton;
import hr.algebra.dao.LoginInfDao;
import hr.algebra.dao.UserDao;
import hr.algebra.model.LoginInf;
import hr.algebra.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Bruno
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("username");
            String password = request.getParameter("password");

            DataSource ds = DataSourceSingleton.getInstance();
            Connection con = ds.getConnection();
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH.mm.ss");
            Date date = new Date();
            
            UserDao uD = new UserDao(ds.getConnection());
            User user = uD.userLogin(email, password);
            LoginInfDao lD = new LoginInfDao(ds.getConnection());
            LoginInf log = new LoginInf();
            
            if (user != null) {
                request.getSession().setAttribute("auth", user);
                
                
                if (user.isAdmin()) {
                    response.sendRedirect("productsAdmin.jsp");
                }
                else{
                    response.sendRedirect("products.jsp");
                }
                
                
                
                String ipAdress = request.getRemoteAddr();
                log.setFirstName(user.getFirstName());
                log.setLastName(user.getLastName());
                log.setDateTime(formatter.format(date));
                log.setIpAdress(ipAdress);
                lD.addLog(log);
                
            } else {
                out.println("there is no user");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
