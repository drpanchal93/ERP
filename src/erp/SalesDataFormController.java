/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class SalesDataFormController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField invNo;

    @FXML
    private TextField invDate;
    
    @FXML
    private TextField buyerName;
    
    @FXML
    private TextField buyerGSTIN;
    
    //Table fields
    @FXML private TableView<SalesData> SalesDataTable;    
    @FXML private TableColumn<SalesData,Integer> serialNo;
    @FXML private TableColumn<SalesData,String> prodDesc;    
    @FXML private TableColumn<SalesData,Integer> quantity;    
    @FXML private TableColumn<SalesData,String> unit;    
    @FXML private TableColumn<SalesData,Integer> basicAmt;    
    @FXML private TableColumn<SalesData,String> taxAmount;    
    @FXML private TableColumn<SalesData,String> totalAmount;
    
    @FXML
    private TextArea salesDataDispatchDetails;
     
    @FXML
    private TextField salesDataRemarks;
    
    @FXML
    private TextField ProductDescription;
    
    @FXML
    private TextField Quantity;
    
    @FXML
    private TextField Unit;
    
    @FXML
    private TextField BasicAmount;
    
    @FXML
    private TextField TaxAmount;
    
   /* @FXML
    private TextField TotalAmount;*/
    
    int sales_count=1;
    
    public ObservableList<SalesData> salesDataList = FXCollections.observableArrayList();
    
    public void changeSrNoCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setSerialNumber((Integer) editedCell.getNewValue());
    }
    
    public void changeProdDescCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setProdDesc((String) editedCell.getNewValue());
    }
    
    public void changeQuantityCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setQuantity((Integer) editedCell.getNewValue());
    }
    
    public void changeUnitCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setUnit((String) editedCell.getNewValue());
    }
    
    public void changeBasicValueCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setBasicAmount((String) editedCell.getNewValue());
    }
    
    public void changeTaxValueCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setTaxAmount((String) editedCell.getNewValue());
    }
    
    public void changeTotalAmountCellEvent(CellEditEvent editedCell)
    {
        SalesData Selected =  SalesDataTable.getSelectionModel().getSelectedItem();
        Selected.setTotalAmount((String) editedCell.getNewValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        serialNo.setCellValueFactory(new PropertyValueFactory<SalesData, Integer>("serialNo"));
        prodDesc.setCellValueFactory(new PropertyValueFactory<SalesData, String>("prodDesc"));
        quantity.setCellValueFactory(new PropertyValueFactory<SalesData, Integer>("quantity"));
        unit.setCellValueFactory(new PropertyValueFactory<SalesData, String>("unit"));
        basicAmt.setCellValueFactory(new PropertyValueFactory<SalesData, String>("basicAmt"));
        taxAmount.setCellValueFactory(new PropertyValueFactory<SalesData, String>("taxAmount"));
        totalAmount.setCellValueFactory(new PropertyValueFactory<SalesData, String>("totalAmount"));
        
        SalesDataTable.setItems(salesDataList);
        
        SalesDataTable.setEditable(true);
        
        serialNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        prodDesc.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        unit.setCellFactory(TextFieldTableCell.forTableColumn());
        basicAmt.setCellFactory(TextFieldTableCell.forTableColumn());
        taxAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        totalAmount.setCellFactory(TextFieldTableCell.forTableColumn());
        
    }    
    
    public void deleteButtonPushed()
    {
        ObservableList<SalesData> selectedRows, allItems;
        allItems = SalesDataTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = SalesDataTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (SalesData item: selectedRows)
        {
            allItems.remove(item);
            sales_count--;
            int c = 1;
            for (SalesData items: allItems)
            {
                items.setSerialNumber(c);
                c++;
            }
        }
    }
    
    public void addButtonPushed()
    {
        //String srNo_string = serialNo.getText();
        //int srNo_int = new Integer(srNo_string);
        String qty_string = Quantity.getText();
        int qty_int = new Integer(qty_string);
        
        String basic_string = BasicAmount.getText();
        int basic_int = new Integer(basic_string);
        
        String tax_string = TaxAmount.getText();
        int tax_int = new Integer(tax_string);
        
        
        int total_int = basic_int + tax_int;
        
        SalesData newEntry = new SalesData(sales_count, ProductDescription.getText(), qty_int,Unit.getText(),basic_int,tax_int,total_int);
        sales_count++;
        
        SalesDataTable.getItems().add(newEntry);
    }
    
}
