/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    
    @FXML
    private AnchorPane salesContactFormAnchorPane;

    Connection conn = DBConnection.democonnection();
    
    public static void setId (int _id) {
        id = _id;
    }
    
    @FXML
    void editContact(MouseEvent event) throws IOException {
        SalesContactDetailsUpdateFXMLController.setId(id);
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SalesContactDetailsUpdate.fxml"));
        salesContactFormAnchorPane.getChildren().setAll(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        
        Statement stmt, stmt1, stmt2, stmt3,stmt4,stmt5,stmt6;
        try {
            stmt = conn.createStatement();
            String query = "select * from CustomerInfo where custInfoId = "+id;
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
                            address.setText(rs.getString("addLine1") + ",\n" + rs.getString("addLine2") + ",\n" + rs1.getString("name") + "," + rs2.getString("name") + ",\n" + rs3.getString("name") + "-" + rs.getString("PinCode"));
                            gstNo.setText(rs.getString("GSTIN"));
                            panNo.setText(rs.getString("PAN"));
                        }
                    }  
               }
               String contactInfo = "";
               stmt4 = conn.createStatement();
               String query4 = "select * from ContactPersonInfo where custInfoId = " + id;
               ResultSet rs4 = stmt4.executeQuery(query4);
               while(rs4.next()) {
                    int contactPersonId = rs4.getInt("contactPersonId");
                    contactInfo += rs4.getString("contactPersonName") + "\n";
                    stmt5 = conn.createStatement();
                    String query5 = "select * from EmailInfo where contactPersonId = " + contactPersonId;
                    ResultSet rs5 = stmt5.executeQuery(query5);
                    int emailCount = 1;
                    while(rs5.next()) {
                        if (emailCount == 1) {
                            contactInfo += "Email:";
                        }
                        contactInfo += " " + rs5.getString("EmailAddress") + "\n";
                        emailCount++;
                    }
                    
                    stmt6 = conn.createStatement();
                    String query6 = "select * from ContactNumberInfo where contactPersonId = " + contactPersonId;
                    ResultSet rs6 = stmt6.executeQuery(query6);
                    int phoneCount = 1;
                    while(rs6.next()) {
                        if (phoneCount == 1) {
                            contactInfo += "Contact:";
                        }
                        contactInfo += " " + rs6.getString("countryCode") + "-"+ rs6.getString("numberAreaCode") + "-" + rs6.getString("contactNumber") + "(" + rs6.getString("contactNumberType") + ")" + "\n";
                        phoneCount++;
                    }
               }
               contact.setText(contactInfo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SalesContactsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
