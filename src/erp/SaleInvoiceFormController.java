/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
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
public class SaleInvoiceFormController implements Initializable 
{
    //All textfields other than table fields
    @FXML private JFXTextField InvoiceNo;
    @FXML private JFXTextField BuyerDetails;
    @FXML private JFXTextField BuyerOrderNo;
    @FXML private JFXTextField DeliveryNoteNo;
    @FXML private JFXTextField DispatchDocumentNo;
    @FXML private JFXTextField DispatchedThrough;
    @FXML private JFXTextField Destination;
    @FXML  private JFXTextField ModeOfPay;
    @FXML private JFXTextField SupplierRef;
    @FXML private JFXTextField OtherRef;
    @FXML private JFXTextField Remarks;
    @FXML private JFXTextField TermsOfDelivery;
    @FXML private JFXTextField ElectronicRefNo;
    @FXML private JFXTextField cgst;
    @FXML private JFXTextField sgst;
    @FXML private JFXTextField igst;
    @FXML private JFXTextField advance;
    @FXML private JFXTextField packaging;
    @FXML private JFXTextField grandTotal;
    @FXML private JFXTextField invoiceValueInWords;
    @FXML private JFXTextField taxAmountInWords;
    @FXML private JFXTextField reverseCharge;
    @FXML private DatePicker InvoiceDate;
    @FXML private DatePicker BuyerOrderDate;
    @FXML private DatePicker DeliveryNoteDate;
    
    //Table fields
    @FXML private TableView<SaleInvoice> SaleInvoiceTable;
    @FXML private TableColumn<SaleInvoice, Integer> serialNumber;
    @FXML private TableColumn<SaleInvoice, String> productDescription;
    @FXML private TableColumn<SaleInvoice, Integer> hsnCode;
    @FXML private TableColumn<SaleInvoice, Integer> quantity;
    @FXML private TableColumn<SaleInvoice, Integer> Rate;
    @FXML private TableColumn<SaleInvoice, String> Per;
    @FXML private TableColumn<SaleInvoice, Integer> TotalAmount;
    
    //textfields below table
    @FXML private TextField prodDesc;
    @FXML private TextField qty;
    @FXML private TextField rt;
    @FXML private TextField per;
    
    //Add and remove buttons
    @FXML private JFXButton addButton;
    @FXML private JFXButton removeButton;
    
    int count=1;
    
    //Observable List
     public ObservableList<SaleInvoice> saleInvoiceList = FXCollections.observableArrayList();
     
     //Edit cell functions to be given on OnEditStart/Commit
     public void changeSrNoCellEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setSerialNumber((Integer) editedCell.getNewValue());
    }
    
    public void changeProdDescriptionEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setProductDescription((String) editedCell.getNewValue());
    }
    
    public void changeQuantityEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setQuantity((Integer) editedCell.getNewValue());
    }
    
    public void changePerEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setPer((String) editedCell.getNewValue());
    }
    
    public void changeRateEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setRate((Integer) editedCell.getNewValue());
    }
    
    public void changeHsnCodeEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setHsnCode((Integer) editedCell.getNewValue());
    }
    
    public void changeTotalAmountEvent(TableColumn.CellEditEvent editedCell)
    {
        SaleInvoice Selected =  SaleInvoiceTable.getSelectionModel().getSelectedItem();
        Selected.setTotalAmount((Integer) editedCell.getNewValue());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        serialNumber.setCellValueFactory(new PropertyValueFactory<SaleInvoice, Integer>("serialNumber"));
        productDescription.setCellValueFactory(new PropertyValueFactory<SaleInvoice, String>("prodDescription"));
        quantity.setCellValueFactory(new PropertyValueFactory<SaleInvoice, Integer>("quantity"));
        hsnCode.setCellValueFactory(new PropertyValueFactory<SaleInvoice, Integer>("hsnCode"));
        Rate.setCellValueFactory(new PropertyValueFactory<SaleInvoice, Integer>("Rate"));
        Per.setCellValueFactory(new PropertyValueFactory<SaleInvoice, String>("Per"));
        TotalAmount.setCellValueFactory(new PropertyValueFactory<SaleInvoice, Integer>("TotalAmount"));
        
        SaleInvoiceTable.setItems(saleInvoiceList);
        
        SaleInvoiceTable.setEditable(true);
        
        serialNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        productDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        hsnCode.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Rate.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Per.setCellFactory(TextFieldTableCell.forTableColumn());
        TotalAmount.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        
    }   
    
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed()
    {
        ObservableList<SaleInvoice> selectedRows, allItems;
        allItems = SaleInvoiceTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = SaleInvoiceTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (SaleInvoice item: selectedRows)
        {
            allItems.remove(item);
            count--;
            int c = 1;
            for (SaleInvoice items: allItems)
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
        String qty_string = qty.getText();
        int qty_int = new Integer(qty_string);
        
        String rate_string = rt.getText();
        int rate_int = new Integer(rate_string);
        
        int totalAmt_int = rate_int * qty_int;
        
        
        int hsnCode_int = 84834000;
        
        SaleInvoice newPerson = new SaleInvoice(count, prodDesc.getText(), qty_int,hsnCode_int,rate_int,per.getText(),totalAmt_int);
        count++;
        
        SaleInvoiceTable.getItems().add(newPerson);
    }  
    
}
