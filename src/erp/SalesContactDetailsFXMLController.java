/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class SalesContactDetailsFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField custName;
    
    @FXML
    private TextField addressLine1;
    
    @FXML
    private TextField addressLine2;
    
    @FXML
    private TextField ctry;
    
    @FXML
    private TextField st;
    
    @FXML
    private TextField cty;
    
    @FXML
    private TextField pCode;
    
    @FXML
    private TextField ctPersonName;
    
    @FXML
    private TextField ctNo;
    
    @FXML
    private TextField eId;
    
    @FXML
    private TextField gstNo;
    
    @FXML
    private TextField panNo;
    
    @FXML private TableColumn<SalesContactDetails,String> Name;
    
    @FXML private TableColumn<SalesContactDetails,String> Email;
    
    @FXML private TableColumn<SalesContactDetails,String> Phone;
    
    @FXML private TableView<SalesContactDetails> SalesContactDetailsTable;
    
    
    @FXML
    void SubmitButtonClicked(ActionEvent event)
    {
        try
        {
            // create a mysql database connection
            Connection conn = DBConnection.democonnection();
            
            
            // Insert data into Customer Info table

            // the mysql insert statement

            String query = " insert into CustomerInfo (customerName,addLine1 , addLine2, Country, State,City,PinCode,GSTIN,PAN)"

              + " values (?,?,?,?,?,?,?,?,?)";


            // create the mysql insert preparedstatement

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, custName.getText());

            preparedStmt.setString (2, addressLine1.getText());

            preparedStmt.setString (3, addressLine2.getText());

            preparedStmt.setString (4, ctry.getText());

            preparedStmt.setString (5, st.getText());

            preparedStmt.setString (6, cty.getText());

            int pCode_int = new Integer(pCode.getText());
            preparedStmt.setInt (7, pCode_int);

            preparedStmt.setString(8, gstNo.getText());

            preparedStmt.setString(9, panNo.getText());

            // execute the preparedstatement

            ResultSet rs = preparedStmt.executeQuery();
            int customerId = 0;

            while(rs.next()){  
              customerId = rs.getInt(1);  
            }

            // Insert data into Contact Person table

            query = " insert into ContactPerson (customerName, customerId)"

              + " values (?,?)";

            // create the mysql insert preparedstatement

            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, ctPersonName.getText());

            preparedStmt.setInt (2, customerId);

            // execute the preparedstatement

            rs = preparedStmt.executeQuery();
            int personId = 0;

            while(rs.next()){  
              personId = rs.getInt(1);  
            }

            // Insert data into Email table

            query = " insert into email (emailId, personId)"

              + " values (?,?)";

            // create the mysql insert preparedstatement

            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, eId.getText());

            preparedStmt.setInt (2, personId);

            // execute the preparedstatement

            preparedStmt.execute();

            // Insert data into Phone table

            query = " insert into phone (phone, personId)"

              + " values (?,?)";

            // create the mysql insert preparedstatement

            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, ctNo.getText());

            preparedStmt.setInt (2, personId);

            // execute the preparedstatement

            preparedStmt.execute();


            conn.close();

        }

        catch (Exception e)

        {

          System.err.println("Got an exception!");

          System.err.println(e.getMessage());

        }
    }
    //User should enter the data and dont know how to implement that
    public ObservableList<SalesContactDetails> ciList = FXCollections.observableArrayList();
    
    public void changeNameEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setName((String) editedCell.getNewValue());
    }
    
    public void changeEmailEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setEmail((String) editedCell.getNewValue());
    }
    
    public void changePhoneEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setPhone((String) editedCell.getNewValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Name.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Name"));
        Email.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Email"));
        Phone.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Phone"));
        
        
        SalesContactDetailsTable.setItems(ciList);
        
        SalesContactDetailsTable.setEditable(true);
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Phone.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
        
      
    /**
     * This method will remove the selected row(s) from the table 
     */
//    public void deleteButtonPushed()
//    {
//        ObservableList<SJO> selectedRows, allItems;
//        allItems = SJOTable.getItems();
//        
//        //this gives us the rows that were selected
//        selectedRows = SJOTable.getSelectionModel().getSelectedItems();
//        
//        //loop over the selected rows and remove the Person objects from the table
//        for (SJO item: selectedRows)
//        {
//            allItems.remove(item);
//            count--;
//            int c = 1;
//            for (SJO items: allItems)
//            {
//                items.setSrNo(c);
//                c++;
//            }
//        }
//    }
//    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */
    public void addButtonPushed()
    {
       
        SalesContactDetails record = new SalesContactDetails(ctPersonName.getText(), eId.getText(), ctNo.getText());
        
        SalesContactDetailsTable.getItems().add(record);
    }
    
}
