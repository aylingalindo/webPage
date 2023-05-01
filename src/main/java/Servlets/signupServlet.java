/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOUsuario;
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
import java.io.Console;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.entidades.Usuario;

/**
 *
 * @author Aylin
 */
@WebServlet(name = "signupServlet", urlPatterns = {"/signupServlet"})
public class signupServlet extends HttpServlet {
    DAOUsuario dao = new DAOUsuario();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("entro al servlet de registro");
            
            String pass = request.getParameter("passSignup");
            String confirmPassword = request.getParameter("confirmPassSignup"); 
            
            Usuario usuNuevo = new Usuario(
                request.getParameter("nameSignup"),
                request.getParameter("pLastnameSignup"),
                request.getParameter("mLastnameSignup"),
                request.getParameter("fecha"),
                   request.getParameter("emailSignup"),
                 request.getParameter("userSignup"),
                        pass,
                request.getParameter("profileImgSignup")
            );
            System.out.println("pass: " + pass);
            System.out.println("confirm pass: " + confirmPassword);
            
            try{ 
                if((pass.equals(confirmPassword))== false){
                    System.out.println("pass not match");
                    request.setAttribute("status", "0");
                    request.setAttribute("err", "2"); 
                    request.setAttribute("err_message", "Passwords do not match");
                } else if( dao.validarExistente(usuNuevo.getUsername(),usuNuevo.getEmail()) == false){
                    System.out.println("username or email taken");
                    System.out.println("res.next");
                    request.setAttribute("status", "0");
                    request.setAttribute("err", "2"); 
                    request.setAttribute("err_message", "The username or email is already taken");
                } else{
                    System.out.println("paso las dos validaciones");
                    if(dao.insertar(usuNuevo)== true){
                        System.out.println("status 1");
                        request.setAttribute("status", "1");
                    }else{
                        System.out.println("status 0");
                        request.setAttribute("status", "0"); 
                        request.setAttribute("err", "2");
                        request.setAttribute("err_message", "Signup failed");
                    }
                }
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
                
            } catch(ServletException | IOException  e){
                System.out.println("catch");
                Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, e);
            }
    }

}

    
