package com.wmnin.wmnin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conexionSQL {
    String url = "jdbc:mysql://localhost:3306/";
    String db = "WomanIn";
    String user = "root";
    String pass = "1234";
    String driver = "com.mysql.jdbc.Driver";
    
    Connection con;
    
    public conexionSQL (){
        
    }
    
    public Connection conectar () throws ClassNotFoundException{
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url+db, user, pass);
            System.out.println("db conectada");
        } catch (SQLException ex) {
            Logger.getLogger(conexionSQL.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("db no conectada");

        }
        return con;
    }
    
    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
