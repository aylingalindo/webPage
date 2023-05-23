/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelos.*;
import modelos.entidades.Publicacion;
import modelos.entidades.Usuario;

/**
 *
 * @author Aylin
 */
public class DAOUsuario implements Operaciones{
    Database db = new Database();
    Usuario usu = new Usuario();
    public static Usuario logged = new Usuario();

    public Object login(Object obj) {
        usu = (Usuario)obj;
        
        Connection con;
        
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM TB_User WHERE username = ? AND `password` = ? ";// insert query sql 
        
        logged.setIdUser(0);
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            System.out.println("DAO USUARIO - Antes del preparedStatement");
            pst = con.prepareStatement(sql);
            pst.setString(1, usu.getUsername());
            pst.setString(2, usu.getPassword());
            rs = pst.executeQuery();
            System.out.println("DAO USUARIO - Before the rs.next");
            while(rs.next()){
                Publicacion post = new Publicacion();
                post.setId_post(0);
                System.out.println("DAO USUARIO - Antes de los logged");
                logged.setIdUser(rs.getInt("id_user")); // al usuario vacio le insertamos lo que retorno el result set en la columna de id_user
                logged.setFirstname(rs.getString("first_name"));
                logged.setpLastname(rs.getString("p_lastname"));
                logged.setmLastname(rs.getString("m_lastname"));
                logged.setBirthdate(rs.getString("birthdate"));
                logged.setEmail(rs.getString("email"));
                logged.setUsername(rs.getString("username"));;
                logged.setPassword(rs.getString("password"));;
                logged.setProfileImg(rs.getString("profile_img"));
                logged.setCoverImg(rs.getString("cover_img"));
                logged.setSingupDate(rs.getString("singup_date"));
                logged.setCity(rs.getString("city"));
                logged.setState(rs.getString("state"));
                logged.setCountry(rs.getString("country"));
                logged.setOccupation(rs.getString("occupation"));
                logged.setUserStatus(rs.getInt("user_status"));
                logged.setCurrent_post(post);
                
                System.out.println("id user DAO " + logged.getIdUser());
                System.out.println("first name DAO " + logged.getFirstname());
                System.out.println("pLastname DAO " + logged.getpLastname());
                System.out.println("mLastname DAO " + logged.getmLastname());
             //   System.out.println("birthdate DAO " + logged.getBirthdate());
              //  System.out.println("email DAO " + logged.getEmail());
               // System.out.println("username DAO " + logged.getUsername());
                //System.out.println("password DAO " + logged.getPassword());
             //   System.out.println("profileImg DAO " + logged.getProfileImg());
              //  System.out.println("coverImg DAO " + logged.getCoverImg());
               // System.out.println("signupDate DAO " + logged.getSingupDate());
                //System.out.println("city DAO " + logged.getCity());
             //   System.out.println("State DAO " + logged.getState());
              //  System.out.println("Country DAO " + logged.getCountry());
               // System.out.println("Occupation DAO " + logged.getOccupation());
               // System.out.println("UserStatus DAO " + logged.getUserStatus());
                
                //pst = con.prepareStatement("SET SQL_SAFE_UPDATES = 0;");
                //pst.executeQuery();
                //pst = con.prepareStatement("DELETE FROM Tb_currentUser;");
                //pst.executeQuery();
                //pst = con.prepareStatement("SET SQL_SAFE_UPDATES = 1;");
                //pst.executeQuery();  
                        
                //pst = con.prepareStatement("INSERT INTO Tb_currentUser(id_user) VALUES ('"  + usu.getUsername() + "')");
                //pst.executeQuery(); 
            } 
            con.close();

        } catch(Exception ex){
            System.out.println("error");
            System.out.println(ex.getMessage());
        } finally{
            return logged;
        }
    }
    
    public Boolean validarExistente(String username, String email) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM TB_User WHERE username = ?";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if(rs.next()){
                con.close();
                return false;
            }
            
            sql = "SELECT * FROM TB_User WHERE email = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()){
                con.close();
                return false;
            }

            con.close();
        } catch(Exception ex){
            System.out.println("error");
            System.out.println(ex.getMessage());
            return false;
        } finally {
            return true;
        }
    }
    
    public Boolean validarExistenteCorreo(String email) {
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM TB_User WHERE email = ?";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()){
                con.close();
                return false;
            }
            con.close();
        } catch(Exception ex){
            System.out.println("error");
            System.out.println(ex.getMessage());
            return false;
        } finally {
            return true;
        }
    }
    
    public Object accessLogged(){
        return logged;
    }
    
    @Override
    public Boolean insertar(Object obj) {
        usu = (Usuario)obj;
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "INSERT INTO TB_User(first_name, p_lastname, m_lastname, birthdate, email, profile_img, username, `password`) VALUES " + "(?,?,?,?,?,?,?,?)";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setString(1, usu.getFirstname());
            pst.setString(2, usu.getpLastname());
            pst.setString(3, usu.getmLastname());
            pst.setString(4, usu.getBirthdate());
            pst.setString(5, usu.getEmail());
            pst.setString(6, usu.getProfileImg());
            pst.setString(7, usu.getUsername());
            pst.setString(8, usu.getPassword());
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
    public boolean modificar(Object obj) {
        usu = (Usuario)obj;
        Connection con;
        PreparedStatement pst;
        String sql = "update tb_user set occupation = ?, city = ?, state = ?, country = ?, email = ?, birthdate = ? where id_user = ?;";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setString(1, usu.getOccupation());
            pst.setString(2, usu.getCity());
            pst.setString(3, usu.getState());
            pst.setString(4, usu.getCountry());
            pst.setString(5, usu.getEmail());
            pst.setString(6, usu.getBirthdate());
            pst.setInt(7,usu.getIdUser());
            int rowCount = pst.executeUpdate();
            
            if(rowCount>0){
                System.out.println("Se ejecuto el query correctamente");
                
                logged.setOccupation(usu.getOccupation());
                logged.setCity(usu.getCity());
                logged.setState(usu.getState());
                logged.setCountry(usu.getCountry());
                logged.setEmail(usu.getEmail());
                logged.setBirthdate(usu.getBirthdate());
                
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
    
    public boolean modificarInfo(Object obj) {
        System.out.println("entro al dao modificar info");
        usu = (Usuario)obj;
        Connection con;
        PreparedStatement pst; 
        String sql = "update tb_user set first_name = ?, p_lastname = ?, m_lastname = ?, username = ?, `password` = ?, profile_img = ? , cover_img = ? where id_user = ?;";
        
        try{
            System.out.println("Paso el query, entro al try");
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setString(1, usu.getFirstname());
            pst.setString(2, usu.getpLastname());
            pst.setString(3, usu.getmLastname());
            pst.setString(4, usu.getUsername());
            pst.setString(5, usu.getPassword());
            pst.setString(6, usu.getProfileImg());
            pst.setString(7,usu.getCoverImg());
            pst.setInt(8,usu.getIdUser());
            int rowCount = pst.executeUpdate();
            
            if(rowCount>0){
                System.out.println("Se ejecuto el query correctamente");
                
                logged.setFirstname(usu.getFirstname());
                logged.setpLastname(usu.getpLastname());
                logged.setmLastname(usu.getmLastname());
                logged.setUsername(usu.getUsername());
                logged.setPassword(usu.getPassword());
                logged.setProfileImg(usu.getProfileImg());
                logged.setCoverImg(usu.getCoverImg());
                
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Object> cobnsultar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
