/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOPublicacion;
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
        String title = request.getParameter("titleNewPost");
        String description = request.getParameter("descriptionNewPost");
        String media = null;
        int idCat = 0;
        // obtener la media y el id_category accediendo a la posicion de la categoria que ya se agrego desde bd. 

            try{
                System.out.println("entró al try dashboardServlet");
                int userPost = loginServlet.logged.getIdUser();
                
                Publicacion publi = new Publicacion(title, description, media, idCat, userPost);
                
                // obtener el id usuario logged Usuario logged = (Usuario) dao.login(usu);
                
                
                RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
                rd.forward(request, response);
            }catch (Exception ex) {
                System.out.println("error");
                System.out.println(ex.getMessage());
            }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
