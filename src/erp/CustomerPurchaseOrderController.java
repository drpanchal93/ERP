/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class CustomerPurchaseOrderController implements Initializable {

    @FXML
    private ComboBox<SalesContacts> from;
    @FXML
    private TextArea custAddress;
    @FXML
    private TextField custGST;
    @FXML
    private TextField poNo;
    @FXML
    private DatePicker poDate;
    @FXML
    private TextArea custContactDetails;
    @FXML
    private TableView<?> SJOTable;
    @FXML
    private TableColumn<?, ?> srNo;
    @FXML
    private TableColumn<?, ?> TableItemDescription;
    @FXML
    private TableColumn<?, ?> TableQuantity;
    @FXML
    private TableColumn<?, ?> TableRate;
    @FXML
    private TableColumn<?, ?> TableUnit;
    @FXML
    private TableColumn<?, ?> TableDiscPercent;
    @FXML
    private TableColumn<?, ?> TableDiscAmount;
    @FXML
    private TableColumn<?, ?> TableTotal;
    @FXML
    private TextField itemDescrip;
    @FXML
    private TextField qty;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton remove;
    @FXML
    private TextField rate;
    @FXML
    private TextField unit;
    @FXML
    private TextField discPercent;
    @FXML
    private TextField discAmt;
    @FXML
    private TextField total;
    @FXML
    private JFXButton custPOSubmit;
    @FXML
    private TextField packFwd;
    @FXML
    private TextField freight;
    @FXML
    private TextArea termsOfDelivery;
    @FXML
    private TextArea termsOfPayment;
    @FXML
    private TextArea remarks;
    @FXML
    private TextArea note;
    @FXML
    private TextField grandTotal;
    @FXML
    private TextField taxAmount;
    @FXML
    private TextField totalAmtWithoutTax;
    @FXML
    private ComboBox<?> sgst;
    @FXML
    private ComboBox<?> cgst;
    @FXML
    private ComboBox<?> igst;
    
    public ObservableList<SalesContacts> fromList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.democonnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        
        //Populating 'From' ComboBox from CustomerInfo table
        
        String  query = "select custInfoId, customerName, addLine1, addLine2, GSTIN from CustomerInfo order by customerName";

            // create the mysql insert preparedstatement

           PreparedStatement preparedStmt;
            try 
            {
                preparedStmt = conn.prepareStatement(query);
                ResultSet rs = preparedStmt.executeQuery();

                while(rs.next())
                {  
                    fromList.add(new SalesContacts(Integer.parseInt(rs.getString("custInfoId")), rs.getString("customerName"), rs.getString("addLine1")+rs.getString("addLine2"), rs.getString("GSTIN")));
                    //System.out.println(rs.getString("name"));
                    
                }
                from.setItems(fromList);
                
                from.setConverter(new StringConverter<SalesContacts>() {

                    @Override
                    public String toString(SalesContacts object) {
                        return object.getName();
                    }
                        
                    @Override
                    public SalesContacts fromString(String string) {
                        return from.getItems().stream().filter(ap -> 
                            ap.getName().equals(string)).findFirst().orElse(null);
                    }
                });
           }
           catch (SQLException ex) 
            {
                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Populating address from 'From' ComboBox
            
            from.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    
                    SalesContacts newFrom = (SalesContacts) newValue;
                    System.out.println(newFrom.getId());
                    
                    custAddress.clear();
                        
                
                    Statement stmt, stmt1, stmt2, stmt3,stmt4,stmt5,stmt6;
                    try {
                        stmt = conn.createStatement();
                        String query = "select * from CustomerInfo where custInfoId = "+ newFrom.getId();
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
                                        custAddress.setText(rs.getString("addLine1") + ",\n" + rs.getString("addLine2") + ",\n" + rs1.getString("name") + "," + rs2.getString("name") + ",\n" + rs3.getString("name") + "-" + rs.getString("PinCode"));
                                        custGST.setText(rs.getString("GSTIN"));
                                    }
                                }  
                           }
                           String contactInfo = "";
                           stmt4 = conn.createStatement();
                           String query4 = "select * from ContactPersonInfo where custInfoId = " + newFrom.getId();
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
                           custContactDetails.setText(contactInfo);
                            //custAddress.setText(new Location(Integer.parseInt(rs.getString("location_id")), rs.getString("name")));
                            //System.out.println(rs.getString("name"));
                        }
                        
                        //preparedStmt.close();
                        rs.close();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
     

                }    
            });
    }    
    
}
