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
@WebServlet(name = "signupServlet", urlPatterns = {"/signupServlet"})
public class signupServlet extends HttpServlet {
    conexionSQL connection = new conexionSQL();
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String name = request.getParameter("nameSignup");
            String pLastname = request.getParameter("pLastnameSignup"); 
            String mLastname = request.getParameter("mLastnameSignup"); 
            String birthdate = request.getParameter("fecha"); 
            String email = request.getParameter("emailSignup");
            String profileImg = request.getParameter("profileImgSignup");
            String username = request.getParameter("userSignup"); 
            String password = request.getParameter("passSignup"); 
            String confirmPassword = request.getParameter("confirmPassSignup"); 
            
            System.out.println(name);
            System.out.println(pLastname);
            System.out.println(mLastname);
            System.out.println(birthdate);
            System.out.println(email);
            System.out.println(profileImg);
            System.out.println(username);
            System.out.println(password);
            
            RequestDispatcher dispatcher = null;
            try{
                System.out.println("nuevo try");   
                
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+"WomanIn", "root", "1234");
                
                String queryVal = "SELECT * FROM TB_User WHERE username = ?";
                PreparedStatement validation = con.prepareStatement(queryVal);
                validation.setString(1, username);
                ResultSet res = validation.executeQuery();
                
                if(password!=confirmPassword){
                    request.setAttribute("status", "0");
                    request.setAttribute("err", "2"); 
                    request.setAttribute("err_message", "Passwords do not match");
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
                
                if(res.next()){
                    System.out.println("res.next");
                    request.setAttribute("status", "0");
                    request.setAttribute("err", "2"); 
                    request.setAttribute("err_message", "This username is already taken");
                    dispatcher = request.getRequestDispatcher("index.jsp");
                    dispatcher.forward(request, response);
                }
                else{
                    System.out.println("else");
                    String queryVal2 = "SELECT * FROM TB_User WHERE email = ?";
                    PreparedStatement validation2 = con.prepareStatement(queryVal2);
                    validation2.setString(1, email);
                    ResultSet res2 = validation2.executeQuery();
                    if(res2.next()){
                        System.out.println("res2");
                        request.setAttribute("status", "0");
                        request.setAttribute("err", "2"); 
                        request.setAttribute("err_message", "This email already has an account");
                        dispatcher = request.getRequestDispatcher("index.jsp");
                        dispatcher.forward(request, response);
                    }
                    else{
                        System.out.println("insertquery");
                        String query = "INSERT INTO TB_User(first_name, p_lastname, m_lastname, birthdate, email, profile_img, username, `password`) VALUES " + "(?,?,?,?,?,?,?,?)";
                        PreparedStatement pst = con.prepareStatement(query);
                        pst.setString(1, name);
                        pst.setString(2, pLastname);
                        pst.setString(3, mLastname);
                        pst.setString(4, birthdate);
                        pst.setString(5, email);
                        pst.setString(6, profileImg);
                        pst.setString(7, username);
                        pst.setString(8, password);

                        int rowCount = pst.executeUpdate();
                        if(rowCount > 0){
                            request.setAttribute("status", "1");
                        }
                        else{
                            request.setAttribute("status", "0");
                        }

                        Object rs = request.getAttribute("status");
                        if(rs == "1"){
                            System.out.println("success");
                            dispatcher = request.getRequestDispatcher("index.jsp");
                            dispatcher.forward(request, response);
                        } else{
                            System.out.println("failed");
                            request.setAttribute("err", "2");
                            request.setAttribute("err_message", "Signup failed");
                            dispatcher = request.getRequestDispatcher("index.jsp");
                            dispatcher.forward(request, response);
                        }
                    }
                }
                
            } catch(ServletException | IOException | ClassNotFoundException | SQLException e){
                System.out.println("catch");
                Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}

    
