/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
    int activeP = 1;
    
    @Override
    public Object insertar(Object obj) {
        publi = (Publicacion) obj;
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "INSERT INTO TB_Posts(title, description, media, post_status, post_user, id_category) VALUES " + "(?,?,?,?,?,?)";
        
        try{
            Class.forName(db.getDriver());
            con = DriverManager.getConnection(db.getUrl()+db.getDb(), db.getUser(), db.getPass());
            
            pst = con.prepareStatement(sql);

            pst.setString(1, publi.getTitle());
            pst.setString(2, publi.getDescription());
            pst.setString(3, publi.getMedia());
            pst.setInt(4, activeP);
            pst.setInt(5, publi.getPost_user());
            pst.setInt(6, publi.getIdCategory());
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
