/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import DAO.DAOPublicacion;
import DAO.DAOUsuario;
import jakarta.servlet.RequestDispatcher;             //MODIFICAR
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;              //MODIFICAR
import jakarta.servlet.annotation.WebServlet;         //MODIFICAR
import jakarta.servlet.http.HttpServlet;              //MODIFICAR
import jakarta.servlet.http.HttpServletRequest;       //MODIFICAR
import jakarta.servlet.http.HttpServletResponse;      //MODIFICAR
import java.util.logging.Level;
import java.util.logging.Logger;
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
    DAOUsuario daoU = new DAOUsuario();
    public Usuario profile = (Usuario)daoU.accessLogged();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entra al do get de POST SERVLET");
        
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();
        //System.out.println("action is " + action);
        
        if("profile".equals(action)){
            //System.out.println("Antes del posts");
            List<Publicacion> posts = dao.consultLatestPostProfile(profile.getIdUser());
            //System.out.println("Post " + posts.get(0).getTitle() + posts.get(0).getDescription());

            JSONObject json = new JSONObject();
            for(int i=0; i<posts.size(); i++){
                JSONObject jsonAux = new JSONObject();
                Usuario uinfo = new Usuario();
                uinfo = posts.get(i).getPost_userdata();           
                jsonAux.put("idPost", posts.get(i).getId_post());
                jsonAux.put("idCat", posts.get(i).getIdCategory());
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
        switch(action){
            case "recents":{
                getLatestPosts(request, out);
                break;
            }
            case "totalPages":{
                getPagination(request, out);
            }
            default:{
                break;
            }
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("entro al do post de POST SERVLET");
        int idUser = profile.getIdUser();
            
        Publicacion editPost = new Publicacion(
         Integer.parseInt(request.getParameter("postIdEdit")),
           request.getParameter("titleEdit"),
      request.getParameter("descriptionEdit"),
           request.getParameter("imgUrlEdit"),
       Integer.parseInt(request.getParameter("catEdit"))
        );
            
        try{ 
            System.out.println("entro al try edit post");
            if(dao.modificar(editPost)== true){
                    System.out.println("status 1");
                    System.out.println("se editó exitosamente el post");
                    request.setAttribute("status", "1");
            }else{
                    System.out.println("status 0");
                    request.setAttribute("status", "0"); 
                    request.setAttribute("err", "2");
                    request.setAttribute("err_message", "Update about failed");
            }
            request.setAttribute("usuario", profile);
            RequestDispatcher rd = request.getRequestDispatcher("user-profile.jsp");
            rd.forward(request, response);  
        }catch (ServletException | IOException ex) {
                System.out.println("error");
                System.out.println(ex.getMessage());
        }
    }
    
    void getLatestPosts(HttpServletRequest request, PrintWriter out){
            System.out.println("entra a funcion GET LATESTS POSTS de post servlet");
            int currentPage = Integer.parseInt(request.getParameter("page"));
            
            List<Publicacion> posts = dao.consultLatestPost(currentPage);
            //System.out.println("Post " + posts.get(0).getTitle() + posts.get(0).getDescription());

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
    
    void getPagination(HttpServletRequest request, PrintWriter out){
        System.out.println("got into getPagination function in postServlet");
        int totalPages = dao.consultPagination();
        out.print(totalPages);
    }
}



