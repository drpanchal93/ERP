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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

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
    private TableView<ItemDetails> ItemTable;
    @FXML
    private TableColumn<ItemDetails, Integer> srNo;
    @FXML
    private TableColumn<ItemDetails, String> TableItemDescription;
    @FXML
    private TableColumn<ItemDetails, Integer> TableQuantity;
    @FXML
    private TableColumn<ItemDetails, Double> TableRate;
    @FXML
    private TableColumn<ItemDetails, String> TableUnit;
    @FXML
    private TableColumn<ItemDetails, Double> TableDiscPercent;
    @FXML
    private TableColumn<ItemDetails, Double> TableDiscAmount;
    @FXML
    private TableColumn<ItemDetails, Double> TableTotal;
    @FXML
    private TableColumn<ItemDetails, Double> tableAmtBeforeDisc;
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
    private TextField amtBeforeDisc;
    @FXML
    private ComboBox<TaxModel> sgst;
    @FXML
    private ComboBox<TaxModel> cgst;
    @FXML
    private ComboBox<TaxModel> igst;
    
    public ObservableList<TaxModel> sgstList = FXCollections.observableArrayList();
    
    public ObservableList<TaxModel> cgstList = FXCollections.observableArrayList();
    
    public ObservableList<TaxModel> igstList = FXCollections.observableArrayList();
    
    public ObservableList<SalesContacts> fromList = FXCollections.observableArrayList();
    
    public ObservableList<ItemDetails> ItemList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.democonnection();
    int count = 1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        
        srNo.setCellValueFactory(new PropertyValueFactory<ItemDetails, Integer>("srNo"));
        TableItemDescription;
        TableQuantity;
        TableRate;
        TableUnit;
        TableDiscPercent;
        TableDiscAmount;
        TableTotal;
        tableAmtBeforeDisc;
        
        
        srNo.setCellValueFactory(new PropertyValueFactory<SJO, Integer>("srNo"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<SJO, String>("itemDescription"));
        quantity.setCellValueFactory(new PropertyValueFactory<SJO, Integer>("quantity"));
        
        
        SJOTable.setItems(sjoList);
        
        SJOTable.setEditable(true);
        srNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        itemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
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
            
            //Populating tax comboBox SGST
            
            String  query1 = "select id, Type, Rate from TaxTable where Type = 'SGST' order by Rate";
            
            PreparedStatement preparedStmt1;
            try 
            {
                preparedStmt1 = conn.prepareStatement(query1);
                ResultSet rs = preparedStmt1.executeQuery();

                while(rs.next())
                {  
                    sgstList.add(new TaxModel(Integer.parseInt(rs.getString("id")), rs.getString("Type"), Double.parseDouble(rs.getString("Rate"))));
                    //System.out.println(rs.getString("name"));
                    
                }
                sgst.setItems(sgstList);
                
                sgst.setConverter(new StringConverter<TaxModel>() {

                    @Override
                    public String toString(TaxModel object) {
                        return Double.toString(object.getTaxRate()) + "%";
                    }
                        
                    @Override
                    public TaxModel fromString(String string) {
                        return sgst.getItems().stream().filter(ap -> 
                            ap.getName().equals(string)).findFirst().orElse(null);
                    }
                });
           }
           catch (SQLException ex) 
            {
                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Populating tax comboBox CGST
            
            String  query2 = "select id, Type, Rate from TaxTable where Type = 'CGST' order by Rate";
            
            PreparedStatement preparedStmt2;
            try 
            {
                preparedStmt2 = conn.prepareStatement(query2);
                ResultSet rs = preparedStmt2.executeQuery();

                while(rs.next())
                {  
                    cgstList.add(new TaxModel(Integer.parseInt(rs.getString("id")), rs.getString("Type"), Double.parseDouble(rs.getString("Rate"))));
                    //System.out.println(rs.getString("name"));
                    
                }
                cgst.setItems(cgstList);
                
                cgst.setConverter(new StringConverter<TaxModel>() {

                    @Override
                    public String toString(TaxModel object) {
                        return Double.toString(object.getTaxRate()) + "%";
                    }
                        
                    @Override
                    public TaxModel fromString(String string) {
                        return cgst.getItems().stream().filter(ap -> 
                            ap.getName().equals(string)).findFirst().orElse(null);
                    }
                });
           }
           catch (SQLException ex) 
            {
                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Populating tax comboBox IGST
            
            String  query3 = "select id, Type, Rate from TaxTable where Type = 'IGST' order by Rate";
            
            PreparedStatement preparedStmt3;
            try 
            {
                preparedStmt3 = conn.prepareStatement(query3);
                ResultSet rs = preparedStmt3.executeQuery();

                while(rs.next())
                {  
                    igstList.add(new TaxModel(Integer.parseInt(rs.getString("id")), rs.getString("Type"), Double.parseDouble(rs.getString("Rate"))));
                    //System.out.println(rs.getString("name"));
                    
                }
                igst.setItems(igstList);
                
                igst.setConverter(new StringConverter<TaxModel>() {

                    @Override
                    public String toString(TaxModel object) {
                        return Double.toString(object.getTaxRate()) + "%";
                    }
                        
                    @Override
                    public TaxModel fromString(String string) 
                    {
                        return igst.getItems().stream().filter(ap -> 
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
            
            rate.textProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    double amt_before_disc = Double.parseDouble(qty.getText()) * Double.parseDouble(newValue.toString());
                    amtBeforeDisc.setText(Double.toString(amt_before_disc));
                }
            });
            
            discPercent.textProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    double disc_amt = Double.parseDouble(amtBeforeDisc.getText()) * Double.parseDouble(newValue.toString()) / 100;
                    discAmt.setText(Double.toString(disc_amt));
                    
                    double total_amt = Double.parseDouble(amtBeforeDisc.getText()) - disc_amt;
                    total.setText(Double.toString(total_amt));
                }
            });
    }    
    
    /**
     * This method will remove the selected row(s) from the table 
     */
    /*public void deleteButtonPushed()
    {
       
        ObservableList<SalesContactDetails> selectedRows, allItems;
        allItems = SalesContactDetailsTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = SalesContactDetailsTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (SalesContactDetails item: selectedRows)
        {
            allItems.remove(item);
            count--;
            int c = 1;
            for (SalesContactDetails items: allItems)
            {
                items.setSrNo(c);
                c++;
            }
        }
    }*/
    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */
    public void addButtonPushed()
    {
        ItemDetails record = new ItemDetails(count, itemDescrip.getText(), Integer.parseInt(qty.getText()), Double.parseDouble(rate.getText()), unit.getText(), Double.parseDouble(amtBeforeDisc.getText()), Double.parseDouble(discPercent.getText()), Double.parseDouble(discAmt.getText()), Double.parseDouble(total.getText()));
        count++;
        ItemTable.getItems().add(record);
    }
    
}
