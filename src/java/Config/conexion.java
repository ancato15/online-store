/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config; 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANDRES
 */
public class conexion {
    Connection con;
    String url = "jdbc:mariadb://localhost:3306/tienda";
    String user = "root";
    String pass = "root";
    
    public Connection getConnection(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());e.getMessage();
        }
        return con;
    }
}
