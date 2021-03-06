/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author admin
 */
public class PurchaseDataFormController implements Initializable {

    //Table View and Table Columns
    @FXML private TableView<PurchaseData> PurchaseDataTable;
    @FXML private TableColumn<PurchaseData, Integer> serialNumber;
    @FXML private TableColumn<PurchaseData, String> prodDescription;
    @FXML private TableColumn<PurchaseData, Integer> quantity;
    @FXML private TableColumn<PurchaseData, String> unit;
    @FXML private TableColumn<PurchaseData, Integer> basicAmount;
    @FXML private TableColumn<PurchaseData, Integer> taxAmount;
    @FXML private TableColumn<PurchaseData, Integer> totalAmount;
    
    //TextFields other that the table entry
    @FXML private TextField PODate;
    @FXML private TextField PONumber;
    @FXML private TextField SupplierName;
    @FXML private TextField TermsOfPay;
    @FXML private TextField ModeOfPay;
    @FXML private TextField DispatchDetails;
    
    //Table entry text fields
    @FXML private TextField ItemDesc;
    @FXML private TextField Qty;
    @FXML private TextField Unt;
    @FXML private TextField BasicAmt;
    @FXML private TextField TaxAmt;
    //@FXML private TextField TotalAmt;
    
    //Add and Remove Buttons
    @FXML private JFXButton addButton;
    @FXML private JFXButton removeButton;
    
    int count=1;

    //Observable List
     public ObservableList<PurchaseData> purchaseDataList = FXCollections.observableArrayList();
     
     //Edit cell functions to be given on OnEditStart/Commit
     public void changeSrNoCellEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setSerialNumber((Integer) editedCell.getNewValue());
    }
    
    public void changeItemDescriptionEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setProdDescription((String) editedCell.getNewValue());
    }
    
    public void changeQuantityEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setQuantity((Integer) editedCell.getNewValue());
    }
    
    public void changeUnitEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setUnit((String) editedCell.getNewValue());
    }
    
    public void changeBasicAmountEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setBasicAmount((Integer) editedCell.getNewValue());
    }
    
    public void changeTaxAmountEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setTaxAmount((Integer) editedCell.getNewValue());
    }
    
    public void changeTotalAmountEvent(TableColumn.CellEditEvent editedCell)
    {
        PurchaseData Selected =  PurchaseDataTable.getSelectionModel().getSelectedItem();
        Selected.setTotalAmount((Integer) editedCell.getNewValue());
    }
     
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serialNumber.setCellValueFactory(new PropertyValueFactory<PurchaseData, Integer>("serialNumber"));
        prodDescription.setCellValueFactory(new PropertyValueFactory<PurchaseData, String>("prodDescription"));
        quantity.setCellValueFactory(new PropertyValueFactory<PurchaseData, Integer>("quantity"));
        unit.setCellValueFactory(new PropertyValueFactory<PurchaseData, String>("unit"));
        basicAmount.setCellValueFactory(new PropertyValueFactory<PurchaseData, Integer>("basicAmount"));
        taxAmount.setCellValueFactory(new PropertyValueFactory<PurchaseData, Integer>("taxAmount"));
        totalAmount.setCellValueFactory(new PropertyValueFactory<PurchaseData, Integer>("totalAmount"));
        
        PurchaseDataTable.setItems(purchaseDataList);
        
        PurchaseDataTable.setEditable(true);
        
        serialNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        prodDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        unit.setCellFactory(TextFieldTableCell.forTableColumn());
        basicAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        taxAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        totalAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }    
    
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed()
    {
        ObservableList<PurchaseData> selectedRows, allItems;
        allItems = PurchaseDataTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = PurchaseDataTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (PurchaseData item: selectedRows)
        {
            allItems.remove(item);
            count--;
            int c = 1;
            for (PurchaseData items: allItems)
            {
                items.setSerialNumber(c);
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
        String qty_string = Qty.getText();
        int qty_int = new Integer(qty_string);
        
        String basic_string = BasicAmt.getText();
        int basic_int = new Integer(basic_string);
        
        String tax_string = TaxAmt.getText();
        int tax_int = new Integer(tax_string);
        
        
        int total_int = basic_int + tax_int;
        
        PurchaseData newPerson = new PurchaseData(count, ItemDesc.getText(), qty_int,Unt.getText(),basic_int,tax_int,total_int);
        count++;
        
        PurchaseDataTable.getItems().add(newPerson);
    }  
    
}
