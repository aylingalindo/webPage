/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOPublicacion;
import static Servlets.loginServlet.logged;
import com.mysql.cj.xdevapi.Statement;
import jakarta.servlet.RequestDispatcher;           //MODIFICAR
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import jakarta.servlet.ServletException;            //MODIFICAR
import jakarta.servlet.annotation.WebServlet;       //MODIFICAR
import jakarta.servlet.http.HttpServlet;            //MODIFICAR
import jakarta.servlet.http.HttpServletRequest;     //MODIFICAR
import jakarta.servlet.http.HttpServletResponse;    //MODIFICAR
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.entidades.Publicacion;
import modelos.entidades.Usuario;


/**
 *
 * @author Aylin
 */
@WebServlet(name = "dashboardServlet", urlPatterns = {"/dashboardServlet"})
public class dashboardServlet extends HttpServlet {
    DAOPublicacion dao = new DAOPublicacion();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("entro al servlet ");
        String title = request.getParameter("titleNewPost");
        String description = request.getParameter("descriptionNewPost");
        System.out.println("titulo: " + title);
        System.out.println("desc: " + description);
        String cat = request.getParameter("cat");
        int icat;
        
        if (cat == null){
            System.out.println("btn null: " + cat);
            icat = 0;
        }else{
            icat = Integer.parseInt(cat);
            System.out.println("btn: " + icat);
        }
        
        
        String media = null;
        // obtener la media y el id_category accediendo a la posicion de la categoria que ya se agrego desde bd. 

            try{
                System.out.println("entró al try dashboardServlet");
                int userPost = logged.getIdUser();
                System.out.println("usuario: " + userPost);
                request.setAttribute("usuario", logged);
                
                Publicacion publi = new Publicacion(title, description, media, icat, userPost);
                
                if(dao.insertar(publi)== true){
                    System.out.println("publi insert sucess");
                    request.setAttribute("insertP", "1");
                }else {
                    System.out.println("publi insert failed");
                    request.setAttribute("insertP", "2");
                    request.setAttribute("err", "2");
                    request.setAttribute("err_message", "New post failed");
                }

                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request, response);
                
            }catch(ServletException | IOException  e){
                System.out.println("catch");
                Logger.getLogger(signupServlet.class.getName()).log(Level.SEVERE, null, e);
            }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
