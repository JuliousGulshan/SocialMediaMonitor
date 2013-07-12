/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialmediamonitor.dao;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author afonso
 */
public class SocialMediaMonitorDAO {
    public static String dbUrl = "jdbc:mysql://localhost:3306/SocialMediaMonitorDB";
    public static Connection conn = null;
    
    @SuppressWarnings("UseSpecificCatch")
    private static void abrirConexao() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(dbUrl, "root", "beta!@#");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private static void fecharConexao() {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }
    
    public void gravarNoBD() throws SQLException{
        abrirConexao();
        System.err.println(conn);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Users");
        stmt.setMaxRows(100);
        ResultSet resultSet = stmt.executeQuery();
        
         while (resultSet.next()) {
             System.out.println(resultSet.getString("nome"));
         }
    }
}
