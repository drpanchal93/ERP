/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class SalesContactDetailsShowFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private static int id = 0;
    
    @FXML
    private Label address;

    @FXML
    private Label contact;
    
    @FXML
    private Label panNo;

    @FXML
    private Label gstNo;

    @FXML
    private Label custName;

    Connection conn = DBConnection.democonnection();
    
    public static void setId (int _id) {
        id = _id;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Statement stmt, stmt1, stmt2, stmt3;
        try {
            stmt = conn.createStatement();
            String query = "select * from CustomerInfo where id = "+id;
            ResultSet rs = stmt.executeQuery(query);
            int count = 1;
            while(rs.next()) {
               stmt1 = conn.createStatement();
               String query1 = "select name,parent_id  from location where location_id = " + rs.getInt("locationId");
               ResultSet rs1 = stmt1.executeQuery(query1);
               while(rs1.next()) {
                    stmt2 = conn.createStatement();
                    String query2 = "select name,parent_id  from location where location_id = " + rs1.getInt("parent_id");
                    ResultSet rs2 = stmt2.executeQuery(query2);
                    while(rs2.next()) {
                        stmt3 = conn.createStatement();
                        String query3 = "select name,parent_id  from location where location_id = " + rs2.getInt("parent_id");
                        ResultSet rs3 = stmt3.executeQuery(query3);
                        while(rs3.next()) {
                            custName.setText(rs.getString("customerName"));
                            address.setText(rs.getString("addLine1") + "," + rs.getString("addLine2") + "," + rs1.getString("name") + "," + rs2.getString("name") + "," + rs2.getString("name") + "," + rs.getString("PinCode"));
                            gstNo.setText(rs.getString("GSTIN"));
                            panNo.setText(rs.getString("PAN"));
                        }
                    }  
               }
               String contactInfo = "";
               String query4 = "select from ContactPersonInfo where custInfoId = " + id;
               ResultSet rs4 = stmt1.executeQuery(query4);
               while(rs4.next()) {
                    contactInfo += rs4.getString(2) + "\n";
                    String query5 = "select from EmailInfo where contactPersonId = " + rs4.getInt(1);
                    ResultSet rs5 = stmt1.executeQuery(query5);
                    int emailCount = 1;
                    while(rs5.next()) {
                        if (emailCount == 1) {
                            contactInfo += "Email:\n";
                        }
                        contactInfo += "     \t" + rs5.getString(2) + "\n";
                        emailCount++;
                    }
                    String query6 = "select from ContactNumberInfo where contactPersonId = " + rs4.getInt(1);
                    ResultSet rs6 = stmt1.executeQuery(query6);
                    int phoneCount = 1;
                    while(rs6.next()) {
                        if (phoneCount == 1) {
                            contactInfo += "Contact:\n";
                        }
                        contactInfo += "       \t" + rs6.getString(2) + "(" + rs6.getString(3) + ")" + "\n";
                        emailCount++;
                    }
               }
               contact.setText(contactInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesContactsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
