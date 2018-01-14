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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Chaitya
 */
public class SalesContactsController implements Initializable {

    @FXML
    private TextField filter;

    @FXML
    private TableColumn<SalesContacts, String> country;

    @FXML
    private TableColumn<SalesContacts, String> city;

    @FXML
    private TableColumn<SalesContacts, String> panNo;

    @FXML
    private TableColumn<SalesContacts, String> name;

    @FXML
    private TableColumn<SalesContacts, String> gstNo;

    @FXML
    private TableColumn<SalesContacts, Integer> id;

    @FXML
    private TableColumn<SalesContacts, String> state;

    @FXML
    private TableView<SalesContacts> salesContactsTable;
    
    public ObservableList<SalesContacts> sclist = FXCollections.observableArrayList();
    public ObservableList<SalesContacts> sclistFiltered = FXCollections.observableArrayList();
    
    Connection conn = DBConnection.democonnection();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        id.setCellValueFactory(new PropertyValueFactory<SalesContacts, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("name"));
        city.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("city"));
        state.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("state"));
        country.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("country"));
        panNo.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("panNo"));
        gstNo.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("gstNo"));
        
        Statement stmt;
        try {
            stmt = conn.createStatement();
            String query = "select * from VendorInfo";
            ResultSet rs = stmt.executeQuery(query);
            int count = 1;
            while(rs.next()) {
                //customerName,addLine1,addLine2,locationId,PinCode,GSTIN,PAN
               String query1 = "select name,parent_id  from location where location_id = " + rs.getInt("locationId");
               ResultSet rs1 = stmt.executeQuery(query1);
               while(rs1.next()) {
                    String query2 = "select name,parent_id  from location where location_id = " + rs1.getInt("location_id");
                    ResultSet rs2 = stmt.executeQuery(query2);
                    while(rs2.next()) {
                        String query3 = "select name,parent_id  from location where location_id = " + rs2.getInt("location_id");
                        ResultSet rs3 = stmt.executeQuery(query3);
                        while(rs3.next()) {
                            sclist.add(new SalesContacts(count, rs.getString("customerName"), rs1.getString("name"), rs2.getString("name"), rs3.getString("name"), rs.getString("PAN"), rs.getString("GSTIN")));
                        }
                    }  
               }
               count++;
            }
            salesContactsTable.setItems(sclist);
        } catch (SQLException ex) {
            Logger.getLogger(SalesContactsController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
            // Listen for text changes in the filter text field
            filter.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    updateFilteredData();
                }
            });
        
    }  
    
    /**
     * Updates the filteredData to contain all data from the masterData that
     * matches the current filter.
     */
    private void updateFilteredData() {
        sclistFiltered.clear();

        for (SalesContacts p : sclist) {
            if (matchesFilter(p)) {
                sclistFiltered.add(p);
            }
        }

        // Must re-sort table after items changed
        //reapplyTableSortOrder();
        salesContactsTable.setItems(sclistFiltered);
    }

    /**
     * Returns true if the person matches the current filter. Lower/Upper case
     * is ignored.
     * 
     * @param SalesContacts
     * @return
     */
    private boolean matchesFilter(SalesContacts vendor) {
        String filterString = filter.getText();
        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (vendor.getName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (vendor.getCity().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (vendor.getState().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (vendor.getCountry().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (vendor.getPanNo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (vendor.getGstNo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }
    
}
