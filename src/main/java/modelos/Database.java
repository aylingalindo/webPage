    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Aylin
 */
public class Database {
    String url = "";
    String db = "";
    String user = "";
    String pass = "";
    String driver = "";

    public Database() {
        this.url = "jdbc:mysql://localhost:3306/";
        this.db = "WomanIn2";        //MODIFICAR
        this.user = "root";
        this.pass = "";         //MODIFICAR
        this.driver = "com.mysql.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public String getDb() {
        return db;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getDriver() {
        return driver;
    }
    
    
    
}
