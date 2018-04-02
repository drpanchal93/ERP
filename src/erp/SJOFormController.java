/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class SJOFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField sjoNo;
   
    @FXML
    private TextField sjoDate;
    
    @FXML
    private TextField custPONo;
    
    @FXML
    private DatePicker custPODate;
    
    @FXML
    private TextArea remarks;
    
    @FXML
    private TextField deliveryDate;
    
//    @FXML
//    private JFXButton sjoSubmit;
    
    @FXML private TableView<SJO> SJOTable;
    
    @FXML private TableColumn<SJO,Integer> srNo;
    
    @FXML private TableColumn<SJO,String> itemDescription;
    
    @FXML private TableColumn<SJO,Integer> quantity;
    
    @FXML private TextField serialNo;
    @FXML private TextField itemDescrip;
    @FXML private TextField qty;
    
    int count = 1;
    
    //User should enter the data and dont know how to implement that
    public ObservableList<SJO> sjoList = FXCollections.observableArrayList();
    
    public void changeSrNoCellEvent(CellEditEvent editedCell)
    {
        SJO Selected =  SJOTable.getSelectionModel().getSelectedItem();
        Selected.setSrNo((Integer) editedCell.getNewValue());
    }
    
    public void changeItemDescriptionEvent(CellEditEvent editedCell)
    {
        SJO Selected =  SJOTable.getSelectionModel().getSelectedItem();
        Selected.setItemDescription((String) editedCell.getNewValue());
    }
    
    public void changeQuantityEvent(CellEditEvent editedCell)
    {
        SJO Selected =  SJOTable.getSelectionModel().getSelectedItem();
        Selected.setQuantity((Integer) editedCell.getNewValue());
    }
    
    @FXML
    void sjoSubmitBtnClicked(ActionEvent event) 
    {

    }
    
    
    Connection conn = DBConnection.democonnection();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        srNo.setCellValueFactory(new PropertyValueFactory<SJO, Integer>("srNo"));
        itemDescription.setCellValueFactory(new PropertyValueFactory<SJO, String>("itemDescription"));
        quantity.setCellValueFactory(new PropertyValueFactory<SJO, Integer>("quantity"));
        
        
        SJOTable.setItems(sjoList);
        
        SJOTable.setEditable(true);
        srNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        itemDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        custPONo.textProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                String po = (String) newValue;
                
                Statement stmt, stmt1, stmt2;
                try {
                    stmt = conn.createStatement();
                    String query = "select PO_Date, Remarks from customerpotable where PO_No = '"+po+"'";
                    ResultSet rs = stmt.executeQuery(query);
                    while(rs.next()) {
                       custPODate.setValue(rs.getDate("PO_Date").toLocalDate());
                       remarks.setText(rs.getString("Remarks"));
                       stmt1 = conn.createStatement();
                       String query1 = "select item_id  from customerpurchaseorder_itemdetails where PO_No = '" +po+"'";
                       ResultSet rs1 = stmt1.executeQuery(query1);
                       while(rs1.next()) {
                            stmt2 = conn.createStatement();
                            String query2 = "select description,quantity  from custpoitemdetailstable where id = " + rs1.getInt("item_id");
                            ResultSet rs2 = stmt2.executeQuery(query2);
                            while (rs2.next()) {
                                SJO newPerson = new SJO(count, rs2.getString("description"), rs2.getInt("quantity"));
                                count++;

                                SJOTable.getItems().add(newPerson);
                            }
                       }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SalesContactsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }    
        
      
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed()
    {
        ObservableList<SJO> selectedRows, allItems;
        allItems = SJOTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = SJOTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (SJO item: selectedRows)
        {
            allItems.remove(item);
            count--;
            int c = 1;
            for (SJO items: allItems)
            {
                items.setSrNo(c);
                c++;
            }
        }
    }
    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */
    public void addButtonPushed()
    {
       
        //String srNo_string = serialNo.getText();
        //int srNo_int = new Integer(srNo_string);
        String qty_string = qty.getText();
        int qty_int = new Integer(qty_string);
        
        SJO newPerson = new SJO(count, itemDescrip.getText(), qty_int);
        count++;
        
        SJOTable.getItems().add(newPerson);
    }    
        /*ObservableList<SJO> selectedRows, allItems;
        allItems = SJOTable.getItems();
        
       
        selectedRows = SJOTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (SJO item: selectedRows)
        {
            allItems.add(null);
            SJOTable.getItems().add(null);
        }*/
    
}
