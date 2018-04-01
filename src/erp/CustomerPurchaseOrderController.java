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
import javafx.util.converter.DoubleStringConverter;
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
    int counter = 1;
    double total_value = 0;
    
    public void changeSrNoCellEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setSerialNumber((Integer) editedCell.getNewValue());
    }
    
    public void changeItemDescriptionEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setItemDescription((String) editedCell.getNewValue());
    }
    
    public void changeQuantityEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setQuantity((Integer) editedCell.getNewValue());
    }
    
    public void changeRateEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setRate((Double) editedCell.getNewValue());
    }
    
    public void changeUnitEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setUnit((String) editedCell.getNewValue());
    }
    
    public void changeAmtBeforeDiscEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setAmtBeforeDisc((Double) editedCell.getNewValue());
    }
    
    public void changeDiscPercentEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setDiscPercent((Double) editedCell.getNewValue());
    }
    
    public void changeDiscAmountEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setDiscAmount((Double) editedCell.getNewValue());
    }
    
    public void changeTotalEvent(TableColumn.CellEditEvent editedCell)
    {
        ItemDetails Selected =  ItemTable.getSelectionModel().getSelectedItem();
        Selected.setTotal((Double) editedCell.getNewValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        srNo.setCellValueFactory(new PropertyValueFactory<ItemDetails, Integer>("serialNumber"));
        TableItemDescription.setCellValueFactory(new PropertyValueFactory<ItemDetails, String>("itemDescription"));
        TableQuantity.setCellValueFactory(new PropertyValueFactory<ItemDetails, Integer>("quantity"));
        TableRate.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("rate"));
        TableUnit.setCellValueFactory(new PropertyValueFactory<ItemDetails, String>("unit"));
        tableAmtBeforeDisc.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("amtBeforeDisc"));
        TableDiscPercent.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("discPercent"));
        TableDiscAmount.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("discAmount"));
        TableTotal.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("total"));
        
//        srNo.setCellValueFactory(new PropertyValueFactory<ItemDetails, Integer>("srNo"));
//        TableItemDescription.setCellValueFactory(new PropertyValueFactory<ItemDetails, String>("TableItemDescription"));
//        TableQuantity.setCellValueFactory(new PropertyValueFactory<ItemDetails, Integer>("TableQuantity"));
//        TableRate.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("TableRate"));
//        TableUnit.setCellValueFactory(new PropertyValueFactory<ItemDetails, String>("TableUnit"));
//        tableAmtBeforeDisc.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("tableAmtBeforeDisc"));
//        TableDiscPercent.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("TableDiscPercent"));
//        TableDiscAmount.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("TableDiscAmount"));
//        TableTotal.setCellValueFactory(new PropertyValueFactory<ItemDetails, Double>("TableTotal"));
        
        
        
        ItemTable.setItems(ItemList);
        
        ItemTable.setEditable(true);
        
        srNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableItemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        TableQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        TableRate.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableUnit.setCellFactory(TextFieldTableCell.forTableColumn());
        tableAmtBeforeDisc.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableDiscPercent.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableDiscAmount.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        TableTotal.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        
        
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
            
            sgst.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    TaxModel tax = (TaxModel) newValue;
                    double s = tax.getTaxRate();
                    double i = 0;
                    if (igst.getValue() != null) {
                        i = igst.getValue().getTaxRate();
                    }     
                    double c = 0;
                    if (cgst.getValue() != null) {
                        c = cgst.getValue().getTaxRate();
                    } 
                    double t = Double.parseDouble(totalAmtWithoutTax.getText());
                    
                    double ta = t * (s+i+c) / 100;
                    double gt = t + ta;
                    
                    taxAmount.setText(Double.toString(ta));
                    grandTotal.setText(Double.toString(gt));
                }  
            });
            
            igst.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    TaxModel tax = (TaxModel) newValue;
                    double i = tax.getTaxRate();
                    double s = 0;
                    if (sgst.getValue() != null) {
                        s = sgst.getValue().getTaxRate();
                    }     
                    double c = 0;
                    if (cgst.getValue() != null) {
                        c = cgst.getValue().getTaxRate();
                    } 
                    
                    double t = Double.parseDouble(totalAmtWithoutTax.getText());
                    
                    double ta = t * (s+i+c) / 100;
                    double gt = t + ta;
                    
                    taxAmount.setText(Double.toString(ta));
                    grandTotal.setText(Double.toString(gt));
                }  
            });
            
            cgst.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    TaxModel tax = (TaxModel) newValue;
                    double c = tax.getTaxRate();
                   double i = 0;
                    if (igst.getValue() != null) {
                        i = igst.getValue().getTaxRate();
                    }     
                    double s = 0;
                    if (sgst.getValue() != null) {
                        s = sgst.getValue().getTaxRate();
                    } 
                    
                    double t = Double.parseDouble(totalAmtWithoutTax.getText());
                    
                    double ta = t * (s+i+c) / 100;
                    double gt = t + ta;
                    
                    taxAmount.setText(Double.toString(ta));
                    grandTotal.setText(Double.toString(gt));
                }  
            });
    }   
    
    public void submitButtonPushed() throws SQLException {
        
        // Insert data into Customer PO table

        // the mysql insert statement

        String query = " insert into customerpotable (PO_No,PO_Date,Customer_Id,TermsOfDelivery,PaymentTerms,Remarks,Note,grandTotal,packFwd,freight,sgst_id,cgst_id,igst_id,taxAmount,totalAmount)"

          + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        // create the mysql insert preparedstatement

        PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        System.out.println(poDate.getValue().toString());
        System.out.println(from.getValue().getId());
        System.out.println(termsOfDelivery.getText());
        System.out.println(termsOfPayment.getText());
        System.out.println(remarks.getText());
        System.out.println(note.getText());
        System.out.println(grandTotal.getText());
        System.out.println(packFwd.getText());
        System.out.println(freight.getText());
        System.out.println(sgst.getValue().getId());
        System.out.println(cgst.getValue().getId());
        System.out.println(igst.getValue().getId());
        System.out.println(taxAmount.getText());
        System.out.println(total.getText());
        
        preparedStmt.setString (1, poNo.getText());
        
        preparedStmt.setDate(2, java.sql.Date.valueOf(poDate.getValue().toString()));

        preparedStmt.setInt(3, from.getValue().getId());

        preparedStmt.setString (4, termsOfDelivery.getText());

        preparedStmt.setString(5, termsOfPayment.getText());

        preparedStmt.setString(6, remarks.getText());
         
        preparedStmt.setString(7, note.getText());
        
        preparedStmt.setDouble(8, Double.parseDouble(grandTotal.getText()));
         
        preparedStmt.setDouble(9, Double.parseDouble(packFwd.getText()));
        
        preparedStmt.setDouble(10, Double.parseDouble(freight.getText()));
        
        preparedStmt.setInt(11, sgst.getValue().getId());
        
        preparedStmt.setInt(12, cgst.getValue().getId());
        
        preparedStmt.setInt(13, igst.getValue().getId());
        
        preparedStmt.setDouble(14, Double.parseDouble(taxAmount.getText()));
        
        preparedStmt.setDouble(15, Double.parseDouble(total.getText()));

        // execute the preparedstatement

        int rs_int = preparedStmt.executeUpdate();

        
        ItemTable.getItems().forEach((ItemDetails item) -> {

        System.out.println(item.getItemDescription());
        // Insert data into Contact Person table

        String query1 = " insert into custpoitemdetailstable (description, quantity, unit, price, discPer, discAmount, totalAmount)"

          + " values (?,?,?,?,?,?,?)";


        try {
            // create the mysql insert preparedstatement

            PreparedStatement preparedStmt1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);

            preparedStmt1.setString (1, item.getItemDescription());

            preparedStmt1.setInt (2, item.getQuantity());

            preparedStmt1.setString (3, item.getUnit());

            preparedStmt1.setDouble (4, item.getRate());

            preparedStmt1.setDouble (5, item.getDiscPercent());

            preparedStmt1.setDouble (6, item.getDiscAmount());

            preparedStmt1.setDouble (7, item.getTotal());

            // execute the preparedstatement

            int rs1_int = preparedStmt1.executeUpdate();

            if (rs1_int == 0) {
                throw new SQLException("Creating item failed, no rows affected.");
            }

            try (ResultSet generatedKeys1 = preparedStmt1.getGeneratedKeys()) {
                if (generatedKeys1.next()) {
                    int itemId = generatedKeys1.getInt(1);
                    // Insert data into Email table

                    String query2 = " insert into customerpurchaseorder_itemdetails (PO_No, item_id)"

                      + " values (?,?)";

                    // create the mysql insert preparedstatement

                    try {
                        PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                        preparedStmt2.setString (1, poNo.getText());

                        preparedStmt2.setInt (2, itemId);

                        // execute the preparedstatement

                        preparedStmt2.execute();
                    } catch (SQLException ex) {
                        Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }catch (SQLException ex) {
                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }catch (SQLException ex) {
            Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    });

    }
    
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed()
    {
       
        ObservableList<ItemDetails> selectedRows, allItems;
        allItems = ItemTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = ItemTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (ItemDetails item: selectedRows)
        {
            allItems.remove(item);
            counter--;
            int c = 1;
            for (ItemDetails items: allItems)
            {
                items.setSerialNumber(c);
                c++;
            }
        }
        
        total_value = 0;
        ItemTable.getItems().forEach((ItemDetails records) -> {
            
            this.Total(records.getTotal());
        });
        totalAmtWithoutTax.setText(Double.toString(total_value));
    }
    
    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */
    public void addButtonPushed()
    {
        ItemDetails record = new ItemDetails(counter, itemDescrip.getText(), new Integer(qty.getText()), new Double(rate.getText()), unit.getText(), new Double(amtBeforeDisc.getText()), new Double(discPercent.getText()), new Double(discAmt.getText()), new Double(total.getText()));
        counter++;
        ItemTable.getItems().add(record);
        
        total_value = 0;
        ItemTable.getItems().forEach((ItemDetails records) -> {
            
            this.Total(records.getTotal());
        });
        totalAmtWithoutTax.setText(Double.toString(total_value));
    }
    
    public void Total(double value) {
        total_value += value;       
    }
    
}
