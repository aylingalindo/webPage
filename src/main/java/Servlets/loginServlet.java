/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Servlets;

import DAO.DAOUsuario;
import modelos.entidades.Usuario;

import jakarta.servlet.RequestDispatcher;               //MODIFICAR
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.ServletException;                //MODIFICAR
import jakarta.servlet.annotation.WebServlet;           //MODIFICAR
import jakarta.servlet.http.HttpServlet;                //MODIFICAR
import jakarta.servlet.http.HttpServletRequest;         //MODIFICAR
import jakarta.servlet.http.HttpServletResponse;        //MODIFICAR
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.entidades.Usuario;

/**
 *
 * @author Aylin
 */


@WebServlet(name = "loginServlet", urlPatterns = {"/loginServlet"})
public class loginServlet extends HttpServlet {
    DAOUsuario dao = new DAOUsuario();
    conexionSQL connection = new conexionSQL();
    public static Usuario logged;
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("Entra al doPost");
            String user = request.getParameter("nameLogin");
            String pass = request.getParameter("passLogin"); 
            
            try{
                String pantalla;
                
                Usuario usu = new Usuario(user, pass);
                logged = (Usuario) dao.login(usu);
                
                if(logged.getIdUser()!=0){
                    System.out.println("rs.next");
                    pantalla = "dashboard.jsp";
                    request.setAttribute("err", 0);
                    System.out.println(logged.getOccupation());
                    request.setAttribute("usuario", logged); // envia al jsp el obj usuario logged con la info del usuario q inicio sesion. 
                }else {
                    System.out.println("Login failed. Return to index.jsp");
                    pantalla = "index.jsp";
                    request.setAttribute("err", 1);
                    request.setAttribute("err_message", "Credenciales equivocadas");
                }
                
                RequestDispatcher rd = request.getRequestDispatcher(pantalla);
                rd.forward(request, response);
                
                //try {
                    //System.out.println("ENTRO AL TRY");
                    
                    //int userID = 0;
                    //int currentID = 0;
                    
                    //if(rs.next()) {
                        //userID = rs.getInt("id_user");
                        //System.out.println(userID);
                        
                        //PreparedStatement pst = con.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
                        //pst.executeUpdate();
                        //PreparedStatement pst3 = con.prepareStatement("DELETE FROM Tb_currentUser;");
                        //pst3.executeUpdate();
                        //PreparedStatement pst4 = con.prepareStatement("SET SQL_SAFE_UPDATES = 1;");
                        //pst4.executeUpdate();    
                        
                        //PreparedStatement pst2 = con.prepareStatement("INSERT INTO Tb_currentUser(id_user) VALUES ('"  + userID + "')");
                        //pst2.executeUpdate();      

                        //String userIDs = "" + userID;
                        //PreparedStatement pst = con.prepareStatement("DELETE FROM Tb_currentUser WHERE NOT id_user = ?");
                        //pst.setString(1, userIDs);
                        //pst.executeUpdate();
                        
                        //System.out.println("ENTRO AL NEXT");
                        //response.sendRedirect("dashboard.jsp");
                        
                        //String name = rs.getString("first_name") + " " + rs.getString("p_lastname");
                        //String foto = rs.getString("profile_img");
                        //System.out.println("name " + name);
                        //System.out.println("foto " + foto);
                        //request.setAttribute("name", name);
                        //request.setAttribute("foto", foto);
                        
                    //} else {
                        //System.out.println("ERRORsito");
                        //request.setAttribute("err", "1");
                        //request.setAttribute("err_message", "Invalid username or password");

                        //RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
                        //rd.forward(request, response);    
                    //}
                //} catch (SQLException ex) {
                //    System.out.println("ENTRO AL CATCH");
                //    Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
                //}
                    
            } catch (Exception ex) {
                System.out.println("error");
                System.out.println(ex.getMessage());
            }
           
           //Test Servlet
           /* RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
            rd.forward(request, response);
            response.sendRedirect("dashboard.jsp");*/
    }

}
