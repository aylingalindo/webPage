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
import java.sql.Date;
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
        String sql = "update tb_posts set title = ?, description = ?, media = ? where id_post = ?;";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setString(1, publi.getTitle());
            pst.setString(2, publi.getDescription());
            pst.setString(3, publi.getMedia());
            pst.setInt(4, publi.getId_post());
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

            pst.setInt(1, 2);
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
        System.out.println("Entra al DAO de consultLatestPosts");
        List<Publicacion> datos = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        int firstPost = (currentPage - 1) * postPerPage;
        
        String sql = "SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, user.first_name, user.p_lastname , user.profile_img \n" +
        "FROM TB_Posts post\n" +
        "    INNER JOIN TB_User user\n" +
        "        ON user.id_user = post.post_user JOIN TB_Catstatus status ON post.post_status = status.id_status  WHERE (post.post_status = 1) ORDER BY post.id_post DESC LIMIT ?, ?;";
        try{
            //System.out.println("Entra al try del DAO de publicacion");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            pst.setInt(1, firstPost);
            pst.setInt(2, postPerPage);
            rs = pst.executeQuery();
            
            while(rs.next()){
                //System.out.println("while del DAO de publicacion");
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
               
        String sql = "SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, user.first_name, user.p_lastname , user.profile_img FROM TB_Posts post  INNER JOIN TB_User user ON user.id_user = post.post_user JOIN TB_Catstatus stat ON post.post_status = stat.id_status  WHERE (post.post_status = 1) AND (user.id_user = ?) ORDER BY post.id_post DESC;";

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
    
    public List<Publicacion> searchResult(String word, String typeSearch, String category, String initialDate, String finalDate){
        List<Publicacion> datos = new ArrayList<>();
        System.out.println("Entra al DAO searchResult");
        Connection con;
        ResultSet rs = null;
        
        try{
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            String procedure;
            CallableStatement st = null;
            if("advanced".equals(typeSearch)){
                System.out.println("DAO searchResult- if advanced");
                Date initial = Date.valueOf(initialDate);
                Date finalD = Date.valueOf(finalDate);
                System.out.println("category " + category);
                System.out.println("initialDate " + initial);
                System.out.println("finalDate " + finalD);
                procedure = "{ CALL advancedSearch(?, ?, ?, ?)}";
                
                st = con.prepareCall(procedure);
                st.setNString(1, word);
                st.setNString(2, category);
                st.setDate(3, initial);
                st.setDate(4, finalD);
                rs = st.executeQuery();
                System.out.println("end of rs " + rs.toString());
            }
            else if("normal".equals(typeSearch)){
                System.out.println("DAO searchResult- if advanced");
                procedure = "{ CALL normalSearch(?,?)}";
                
                st = con.prepareCall(procedure);
                st.setNString(1, "A");
                st.setNString(2, word);
                rs = st.executeQuery();
            }
            
           // String procedure = "{ CALL normalSearch(?,?)}";
           // CallableStatement st = con.prepareCall(procedure);
                
           // st.setNString(1, "A");
           // st.setNString(2, word);
           /*if(st != null){
                rs = st.executeQuery();
           }*/
            
            if(rs != null){
                    while(rs.next()){
                    System.out.println("while del DAO de busqueda");
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
                    System.out.println("Datos del post coincidente con " + word + ": ");
                    System.out.println(word + ": " + post.getTitle());
                    System.out.println(" ");
                    datos.add(post);
                }
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
    
    public List<Publicacion> searchResult(String word){
        List<Publicacion> datos = new ArrayList<>();
        System.out.println("Entra al DAO searchResult");
        Connection con;
        ResultSet rs;
        
        try{
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            String procedure = "{ CALL normalSearch(?,?)}";
            CallableStatement st = con.prepareCall(procedure);
                
            st.setNString(1, "A");
            st.setNString(2, word);
            rs = st.executeQuery();
            
            while(rs.next()){
                System.out.println("while del DAO de busqueda");
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
                System.out.println("Datos del post coincidente con " + word + ": ");
                System.out.println(word + ": " + post.getTitle());
                System.out.println(" ");
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
    
    public Publicacion consultEdit(int id){
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "select title, description, media from tb_posts where id_post= ?;";
        Publicacion publi = new Publicacion();
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()){
                
                publi.setId_post(id);
                publi.setTitle(rs.getString("title"));
                publi.setDescription(rs.getString("description"));
                publi.setMedia(rs.getString("media"));
                System.out.println("title: " + publi.getTitle());
                System.out.println("description: " + publi.getDescription());
                System.out.println("media: " + publi.getMedia());
            }
            con.close();
        } catch(Exception ex){
            System.out.println("error");
            System.out.println(ex.getMessage());
        }
        return publi;
    }
    
     public List<Publicacion> categoryFilter(String category){     
        System.out.println("Entra al DAO de categoryFilter");
        List<Publicacion> datos = new ArrayList<>();
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
                
        String sql = "SELECT post.id_post, post.id_category, post.title, post.description, post.media, post.post_status, post.post_user, \n" +
"                userInfo.first_name, userInfo.p_lastname , userInfo.profile_img, cat.category\n" +
"        FROM TB_Posts post \n" +
"        INNER JOIN TB_User userInfo \n" +
"        ON userInfo.id_user = post.post_user\n" +
"        INNER JOIN TB_Category cat\n" +
"        ON post.id_category = cat.id_category \n" +
"        WHERE (cat.category = ?)\n" +
"        ORDER BY post.id_post DESC";
                
                
            try{
            //System.out.println("Entra al try del DAO de publicacion");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            pst.setString(1, category);
            rs = pst.executeQuery();
            
            while(rs.next()){
                //System.out.println("while del DAO de publicacion");
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
}
