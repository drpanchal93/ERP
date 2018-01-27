/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;




/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class PurchaseContactsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField filter;

    @FXML
    private TableColumn<SalesContacts, String> city;

    @FXML
    private TableColumn<SalesContacts, String> name;

    @FXML
    private TableColumn<SalesContacts, String> gstNo;

    @FXML
    private TableColumn<SalesContacts, Integer> id;

    @FXML
    private TableView<SalesContacts> salesContactsTable;
    
    @FXML
    private AnchorPane purchaseContactsAnchorPane;

    @FXML
    private JFXButton addPurchaseContactButton;
    
    @FXML
    private ScrollPane salesConatctScrollPane;
    
    public ObservableList<SalesContacts> sclist = FXCollections.observableArrayList();
    public ObservableList<SalesContacts> sclistFiltered = FXCollections.observableArrayList();
    
    
    
    Connection conn = DBConnection.democonnection();
    
    @FXML
    void addPurchaseContactButtonClicked(ActionEvent event) throws IOException 
    {
        /*Parent salesData = FXMLLoader.load(getClass().getResource("SalesContactDetails.fxml"));
        salesConatctScrollPane.setContent(salesData);*/
        /*URL url = getClass().getResource("SalesContactDetails.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        Node node = (Node) loader.load();*/
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("PurchaseContactDetails.fxml"));
        purchaseContactsAnchorPane.getChildren().setAll(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        // TODO
        city.setCellFactory (col -> {
            TableCell<SalesContacts, String> cell = new TableCell<SalesContacts, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null) {
                           Text text = new Text(item);
                           text.setWrappingWidth(col.getPrefWidth() - 35);
                           this.setPrefHeight(text.getLayoutBounds().getHeight()+10);
                           this.setGraphic(text);
                    }
                }
            };
            return cell;
        });

        
        id.setCellValueFactory(new PropertyValueFactory<SalesContacts, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("name"));
        city.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("address"));
        gstNo.setCellValueFactory(new PropertyValueFactory<SalesContacts, String>("gstNo"));
        
        
        Statement stmt, stmt1, stmt2, stmt3;
        try {
            stmt = conn.createStatement();
            String query = "select * from VendorInfo";
            ResultSet rs = stmt.executeQuery(query);
            int count = 1;
            while(rs.next()) {
                //customerName,addLine1,addLine2,locationId,PinCode,GSTIN,PAN
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
                            sclist.add(new SalesContacts(count, rs.getString("VendorName"), rs.getString("vendAddLine1") + "," + rs.getString("vendAddLine2") + "," + rs1.getString("name") + "," + rs2.getString("name") + "," + rs2.getString("name") + "," + rs.getString("pCode"), rs.getString("GSTIN")));
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
        } else if (vendor.getAddress().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (vendor.getGstNo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    
    }    
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    
    
    /*public void addSalesContactButtonClicked(ActionEvent event) 
    {
        Parent goToSceneTwo = null;
            try 
            {
                goToSceneTwo = FXMLLoader.load(getClass().getResource("SalesContactDetails.fxml"));
            } catch (IOException ex) 
            {
                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene QualityDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) addSalesContactButton.getScene().getWindow() ;
            app_Stage.setScene(QualityDashboard);
            
            //Css code
            //QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("qualitydashboard.css").toExternalForm());
            
            app_Stage.setMaximized(true);
            app_Stage.show();
    }*/
    
    /**
     * Initializes the controller class.
     */
    
    

