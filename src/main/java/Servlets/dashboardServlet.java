/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOPublicacion;
import DAO.DAOUsuario;
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
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.entidades.Publicacion;
import modelos.entidades.Usuario;
import org.json.simple.JSONObject;


/**
 *
 * @author Aylin
 */
@WebServlet(name = "dashboardServlet", urlPatterns = {"/dashboardServlet"})
public class dashboardServlet extends HttpServlet {
    DAOPublicacion dao = new DAOPublicacion();
    DAOUsuario daoU = new DAOUsuario();
    Usuario logged = (Usuario)daoU.accessLogged();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entra al do get DASHBOARD");
        request.setAttribute("usuario", logged);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);  
        
        
        String word = request.getParameter("search");
        System.out.println("word is " + word);
        searchResults(request, out);
        out.close();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("entro al servlet do Post de DASHBOARD SERVLET");
        String title = request.getParameter("titleNewPost");
        String description = request.getParameter("descriptionNewPost");
        String media = request.getParameter("mediaNewPost");
        //System.out.println("titulo: " + title);
        //System.out.println("desc: " + description);
        String cat = request.getParameter("cat");
        int icat = 0;
        
        if (cat == null){
            //System.out.println("btn null: " + cat);
        }else{
            icat = Integer.parseInt(cat);
            //System.out.println("btn: " + icat);
        }

            try{
              //  System.out.println("entró al try dashboardServlet");
                int userPost = logged.getIdUser();
              //  System.out.println("usuario: " + userPost);
                request.setAttribute("usuario", logged);
                
                Publicacion publi = new Publicacion(title, description, media, icat, userPost);
                
                if(dao.insertar(publi)== true){
              //      System.out.println("publi insert sucess");
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
    
    
    void searchResults(HttpServletRequest request, PrintWriter out){
        System.out.println("LLEGUE DESDE LA BUSQUEDA!!!!!");
        String searchWord = request.getParameter("search");
        
        List<Publicacion> posts = dao.searchResult(searchWord);
        JSONObject json = new JSONObject();
            for(int i=0; i<posts.size(); i++){
                JSONObject jsonAux = new JSONObject();
                Usuario uinfo = new Usuario();
                uinfo = posts.get(i).getPost_userdata();           
                jsonAux.put("idPost", posts.get(i).getId_post());
                jsonAux.put("title", posts.get(i).getTitle());
                jsonAux.put("description", posts.get(i).getDescription());
                jsonAux.put("media", posts.get(i).getMedia());
                jsonAux.put("postStatus", posts.get(i).getPost_status());
                jsonAux.put("postUser", posts.get(i).getPost_user());
                jsonAux.put("postUserFirstname", uinfo.getFirstname());
                jsonAux.put("postUserpLastname", uinfo.getpLastname());
                jsonAux.put("postUserPfp", uinfo.getProfileImg());

                json.put(i, jsonAux);
                System.out.println("Imprimiendo posts desde dashboard servlet: " );
                System.out.println("Post: " + jsonAux.toString());
                System.out.println(" " );

            }
            out.print(json);
            System.out.println("End of search results function in dashboard servlet" );
            
    }

}
