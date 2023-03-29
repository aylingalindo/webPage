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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Aylin
 */
@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {

    conexionSQL connection = new conexionSQL();
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String user = request.getParameter("nameLogin");
            String pass = request.getParameter("passLogin"); 
            
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection.conectar();
                Connection con;
                try {
                    System.out.println("ENTRO AL TRY");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"WomanIn", "root", "1234");
                    java.sql.Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM TB_User WHERE username = '"+ user +"' AND `password` = '"+ pass +"'");
                    System.out.println("Paso el query");

                    if(rs.next()) {
                        System.out.println("ENTRO AL NEXT");
                        response.sendRedirect("dashboard.jsp");
                    } else {
                        System.out.println("ERRORsito");
                        request.setAttribute("err", "1");
                        request.setAttribute("err_message", "Invalid username or password");

                        RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
                        rd.forward(request, response);    
                    }
                } catch (SQLException ex) {
                    System.out.println("ENTRO AL CATCH");
                    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            } catch (ClassNotFoundException ex) {
                    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
