/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOPublicacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;              //MODIFICAR
import jakarta.servlet.annotation.WebServlet;         //MODIFICAR
import jakarta.servlet.http.HttpServlet;              //MODIFICAR
import jakarta.servlet.http.HttpServletRequest;       //MODIFICAR
import jakarta.servlet.http.HttpServletResponse;      //MODIFICAR
import modelos.entidades.Publicacion;
import modelos.entidades.Usuario;
import org.json.simple.JSONObject;

/**
 *
 * @author miche
 */
@WebServlet(name = "postServlet", urlPatterns = {"/postServlet"})
public class postServlet extends HttpServlet {

    DAOPublicacion dao = new DAOPublicacion();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entra al do get");
        

        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        System.out.println("action is " + action);

        if("recents".equals(action)){
            System.out.println("Antes del posts");
            List<Publicacion> posts = dao.consultLatestPost();
            System.out.println("Post " + posts.get(0).getTitle() + posts.get(0).getDescription());

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
                //System.out.println("JSON  " + json.toJSONString());
                //System.out.println("JSON llego al final de json" + i);
            }
            out.print(json);
        }
        /*switch(action){
            case "recents":{
                getLatestPosts(request);
                break;
            }
            default:{
                break;
            }
        }*/
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
