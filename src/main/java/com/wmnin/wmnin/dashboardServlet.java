/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wmnin.wmnin;

import com.mysql.cj.xdevapi.Statement;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Aylin
 */
@WebServlet(name = "dashboardServlet", urlPatterns = {"/dashboardServlet"})
public class dashboardServlet extends HttpServlet {

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
            out.println("<title>Servlet dashboardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dashboardServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("titleNewPost");
        String description = request.getParameter("descriptionNewPost");

        RequestDispatcher dispatcher = null;
            try{
                System.out.println("dashboard- try");   
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"WomanIn", "root", "1234");
                
                java.sql.Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT MAX(id_user) FROM Tb_currentUser");
                
                int userInt = rs.getInt("MAX(id_user)");
                
                System.out.println(userInt);
                System.out.println("insertquery dashboard");
                System.out.println("title dashboard" + title);
                System.out.println("description dashboard" + description);
                
                String user = "" + userInt;
                
                String query = "INSERT INTO TB_Posts(title, description, post_status, post_user) VALUES " + "(?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, title);
                pst.setString(2, description);
                pst.setString(3, "1");
                pst.setString(4, user);



                pst.executeUpdate();
              
            } catch(ClassNotFoundException | SQLException e){
                System.out.println("catch");
                Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, e);
        }
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
