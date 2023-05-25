/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOPublicacion;
import DAO.DAOUsuario;

import javax.servlet.RequestDispatcher;               //MODIFICAR
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;                //MODIFICAR
import javax.servlet.annotation.WebServlet;           //MODIFICAR
import javax.servlet.http.HttpServlet;                //MODIFICAR
import javax.servlet.http.HttpServletRequest;         //MODIFICAR
import javax.servlet.http.HttpServletResponse;        //MODIFICAR
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.entidades.Publicacion;
import modelos.entidades.Usuario;
import org.json.simple.JSONObject;


@WebServlet(name = "profileServlet", urlPatterns = {"/profileServlet"})
public class profileServlet extends HttpServlet {
    DAOUsuario daoU = new DAOUsuario();
    DAOPublicacion daoP = new DAOPublicacion();
    public Usuario profile = (Usuario)daoU.accessLogged();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entra al do get PROFILE");
        request.setAttribute("usuario", profile);
        RequestDispatcher rd = request.getRequestDispatcher("user-profile.jsp");
        rd.forward(request, response);  
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            System.out.println("entro al doPost de profile");
            
            String opc = request.getParameter("hiddenOpc");
            System.out.println(opc);
            
            if (opc.equals("1")){
                System.out.println("entro al if de opc 1");
                // hacer el do post de editar info perfil
                try{ 
                    //System.out.println("entro al try post profile info");
                    int idUser = profile.getIdUser();
                    String confirmPass = request.getParameter("validationConfirmPass");
                    String pass = request.getParameter("validationPassword");
             
                    Usuario usuAbout = new Usuario(
                        idUser,
                    request.getParameter("validationName"),
                    request.getParameter("validationFirstLN"),
                    request.getParameter("validationSecondLN"),
                    request.getParameter("validationUsername"),
                    pass,
                    request.getParameter("profileImgEdit"),
                        request.getParameter("backgroundImgEdit")
                    );
            
                    
                    if((pass.equals(confirmPass))== false){
                        System.out.println("pass not match");
                        request.setAttribute("status", "0");
                        request.setAttribute("err", "2"); 
                        request.setAttribute("err_message", "Passwords do not match");
                    } else{
                        System.out.println("paso la validacion");
                        if(daoU.modificarInfo(usuAbout)== true){
                            System.out.println("status 1");
                            request.setAttribute("status", "1");
                        }else{
                            System.out.println("status 0");
                            request.setAttribute("status", "0"); 
                            request.setAttribute("err", "2");
                            request.setAttribute("err_message", "Update about failed");
                        }
                    }
                    request.setAttribute("usuario", profile);
                    RequestDispatcher rd = request.getRequestDispatcher("user-profile.jsp");
                    rd.forward(request, response);  
                    
                } catch(ServletException | IOException  e){
                System.out.println("catch");
                Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            } else if (opc.equals("2")){
                // hacer el do post de editar about
                
                int idUser = profile.getIdUser();
                String usernameUser = profile.getUsername();
            
            
                Usuario usuAbout = new Usuario(
                    idUser,
                    request.getParameter("fechaAbout"),
                    request.getParameter("emailAbout"),
                    request.getParameter("cityAbout"),
                    request.getParameter("stateAbout"),
                    request.getParameter("countryAbout"),
                    request.getParameter("OcupationAbout")
                );
            
                try{ 
                    System.out.println("entro al try post profile");
                    if( daoU.validarExistenteCorreo(usuAbout.getEmail()) == false){
                        System.out.println("email taken");
                        System.out.println("res.next");
                        request.setAttribute("status", "0");
                        request.setAttribute("err", "2"); 
                        request.setAttribute("err_message", "The email is already taken");
                    } else{
                        System.out.println("paso la validacion");
                        if(daoU.modificar(usuAbout)== true){
                            System.out.println("status 1");
                            request.setAttribute("status", "1");
                        }else{
                            System.out.println("status 0");
                            request.setAttribute("status", "0"); 
                            request.setAttribute("err", "2");
                            request.setAttribute("err_message", "Update about failed");
                        }
                    }
                request.setAttribute("usuario", profile);
                RequestDispatcher rd = request.getRequestDispatcher("user-profile.jsp");
                rd.forward(request, response);  
                
                } catch(ServletException | IOException  e){
                System.out.println("catch");
                Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, e);
                }
            }
    }
}
