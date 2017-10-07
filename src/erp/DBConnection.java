/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class DBConnection {
    
    public static Connection democonnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/sample_login?zeroDateTimeBehavior=convertToNull";
            //String url = "jdbc:sqlserver://localhost:1433;databasename=ryitTerminalData_dev;username=sa;password=cr3@t1v3mgmtsa";
            con = DriverManager.getConnection(url, "root", "1234");
            System.out.println("Yaay conn ");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
