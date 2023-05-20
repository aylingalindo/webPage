/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Database;
import modelos.Operaciones;
import modelos.entidades.Publicacion;
import modelos.entidades.Usuario;

/**
 *
 * @author Aylin
 */
public class DAOPublicacion implements Operaciones{
    Database db = new Database();
    Publicacion publi = new Publicacion();
    int activeP;
    int postPerPage = 3;
    
    @Override
    public Boolean insertar(Object obj) {
        activeP = 1;
        publi = (Publicacion) obj;
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "INSERT INTO TB_Posts(title, description, media, post_status, post_user, id_category) VALUES " + "(?,?,?,?,?,?)";
        
        try{
            System.out.println("Paso el query, entro al try DASHBOARD, INSERT PUBLI");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            
            System.out.println(publi.getTitle() + publi.getDescription() + publi.getMedia() + activeP + publi.getPost_user() + publi.getIdCategory());

            pst.setString(1, publi.getTitle());
            pst.setString(2, publi.getDescription());
            pst.setString(3, publi.getMedia());
            pst.setInt(4, activeP);
            pst.setInt(5, publi.getPost_user());
            pst.setInt(6, publi.getIdCategory());
            int rowCount = pst.executeUpdate();
            
            if(rowCount>0){
                System.out.println("Se ejecuto el query correctamente DAO INSERT PUBLI");
                con.close();
                return true;
            }
            con.close();
        } catch(Exception ex){
            System.out.println("error DAO INSERT PUBLI");
            System.out.println(ex.getMessage());
        }
            
        return false; 
    }

    @Override
    public boolean modificar(Object obj) {
        publi = (Publicacion) obj;
        Connection con;
        PreparedStatement pst;
        String sql = "update tb_posts set title = ?, description = ?, media = ?, id_category = ? where id_post = ?;";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setString(1, publi.getTitle());
            pst.setString(2, publi.getDescription());
            pst.setString(3, publi.getMedia());
            pst.setInt(4, publi.getIdCategory());
            pst.setInt(5, publi.getId_post());
            int rowCount = pst.executeUpdate();
            
            if(rowCount>0){
                System.out.println("Se ejecuto el query correctamente");
                con.close();
                return true;
            }
            con.close();
        } catch(Exception ex){
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(Object obj) {
        publi = (Publicacion) obj;
        Connection con;
        PreparedStatement pst;
        String sql = "update tb_posts set post_status = ? where id_post = ?;";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setInt(1, publi.getPost_status());
            pst.setInt(2, publi.getId_post());
            int rowCount = pst.executeUpdate();
            
            if(rowCount>0){
                System.out.println("Se ejecuto el query correctamente");
                con.close();
                return true;
            }
            con.close();
        } catch(Exception ex){
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public ArrayList<Object> cobnsultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<Publicacion> consultLatestPost(int currentPage){     
        System.out.println("Entra al DAO de publicacion");
        List<Publicacion> datos = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        int firstPost = (currentPage - 1) * postPerPage;
        
        String sql = "SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, user.first_name, user.p_lastname , user.profile_img \n" +
        "FROM TB_Posts post\n" +
        "    INNER JOIN TB_User user\n" +
        "        ON user.id_user = post.post_user LIMIT ?, ? ;";
        try{
            System.out.println("Entra al try del DAO de publicacion");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, firstPost);
            pst.setInt(2, postPerPage);
            rs = pst.executeQuery();
            
            while(rs.next()){
                System.out.println("while del DAO de publicacion");
                Publicacion post = new Publicacion();
                Usuario uinfo = new Usuario();
                post.setId_post(rs.getInt("id_post")); //nombre de la columna de la db
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setMedia(rs.getString("media"));
                post.setPost_status(rs.getInt("post_status"));
                post.setPost_user(rs.getInt("post_user"));
                post.setIdCategory(rs.getInt("id_category"));
                uinfo.setFirstname(rs.getString("first_name")); 
                uinfo.setpLastname(rs.getString("p_lastname")); 
                uinfo.setProfileImg(rs.getString("profile_img")); 
                post.setPost_userdata(uinfo);
                
                datos.add(post);
                
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPublicacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err " +  ex);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOPublicacion.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("err2 " +  ex);
        }
        return datos;
    }
    
    public List<Publicacion> consultLatestPostProfile(int loggedUserId){
        System.out.println("Entra al DAO de publicacion PERFIL");
        List<Publicacion> datos = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
               
        String sql = "SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, user.first_name, user.p_lastname , user.profile_img \n" +
        "FROM TB_Posts post\n" +
        "    INNER JOIN TB_User user\n" +
        "        ON user.id_user = post.post_user " +
        "     WHERE post.post_user = ?;";
        try{
            System.out.println("Entra al try del DAO de publicacion");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, loggedUserId);
            rs = pst.executeQuery();
            
            while(rs.next()){
                System.out.println("while del DAO de publicacion");
                Publicacion post = new Publicacion();
                Usuario uinfo = new Usuario();
                post.setId_post(rs.getInt("id_post")); //nombre de la columna de la db
                post.setTitle(rs.getString("title"));
                post.setDescription(rs.getString("description"));
                post.setMedia(rs.getString("media"));
                post.setPost_status(rs.getInt("post_status"));
                post.setPost_user(rs.getInt("post_user"));
                uinfo.setFirstname(rs.getString("first_name")); 
                uinfo.setpLastname(rs.getString("p_lastname")); 
                uinfo.setProfileImg(rs.getString("profile_img")); 
                post.setPost_userdata(uinfo);
                
                datos.add(post);
                
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPublicacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err " +  ex);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOPublicacion.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("err2 " +  ex);
        }
        return datos;
    }
    
    public int consultPagination(){
        System.out.println("Entra al DAO consultPagination");
        Connection con;
        int totalPages = 0;
        try{
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            System.out.println("DAO publicacion - Antes de el procedure");
            String procedure = "{ CALL Pagination(?,?)}";
            CallableStatement st = con.prepareCall(procedure);
                
            st.setInt(1, postPerPage);
            st.registerOutParameter(2, Types.INTEGER);
            st.execute();
            
            totalPages = st.getInt(2);
            System.out.println("DAO publicacion - total pages: " + totalPages);
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPublicacion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("err " +  ex);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOPublicacion.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println("err2 " +  ex);
        }
        return totalPages;
    }
}
