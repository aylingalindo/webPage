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
                    
                    int userID = 0;
                    int currentID = 0;
                    
                    if(rs.next()) {
                        userID = rs.getInt("id_user");
                        System.out.println(userID);
                        
                        PreparedStatement pst = con.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
                        pst.executeUpdate();
                        PreparedStatement pst3 = con.prepareStatement("DELETE FROM Tb_currentUser;");
                        pst3.executeUpdate();
                        PreparedStatement pst4 = con.prepareStatement("SET SQL_SAFE_UPDATES = 1;");
                        pst4.executeUpdate();    
                        
                        PreparedStatement pst2 = con.prepareStatement("INSERT INTO Tb_currentUser(id_user) VALUES ('"  + userID + "')");
                        pst2.executeUpdate();      

                        //String userIDs = "" + userID;
                        //PreparedStatement pst = con.prepareStatement("DELETE FROM Tb_currentUser WHERE NOT id_user = ?");
                        //pst.setString(1, userIDs);
                        //pst.executeUpdate();
                        
                        System.out.println("ENTRO AL NEXT");
                        response.sendRedirect("dashboard.jsp");
                        
                        String name = rs.getString("first_name") + " " + rs.getString("p_lastname");
                        String foto = rs.getString("profile_img");
                        System.out.println("name " + name);
                        System.out.println("foto " + foto);
                        request.setAttribute("name", name);
                        request.setAttribute("foto", foto);
                        
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
