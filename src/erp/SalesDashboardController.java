/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class SalesDashboardController extends ReusableCode implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private BorderPane MainBorderPane;
    
    @FXML
    private HBox footer;

    @FXML
    private HBox header;

    @FXML
    private AnchorPane sideMenu;
    
    @FXML
    private AnchorPane content;
    
    @FXML
    private AnchorPane contentAnchorPane;
     
    @FXML
    private ScrollPane contentScrollPane;
      
    @FXML
    private MenuButton SalesMenuButton;
      
    @FXML
    private JFXButton GenerateSJOButton;
    
    @FXML
    private JFXButton GenerateTaxInvoiceButton;

    @FXML
    private JFXButton GenerateChallanButton;

    @FXML
    private JFXButton SalesHistoryButton;

    @FXML
    private Button GoToDashboardButton;
     
    @FXML
    void GenerateSJOButtonClick(ActionEvent event) throws IOException
    {
       //content.getChildren().setAll(FXMLLoader.load(/Users/drashtipanchal/Documents/Drashti/NetBeansProjects/src/erp));
       Parent salesData = FXMLLoader.load(getClass().getResource("SJOForm.fxml"));
       contentScrollPane.setContent(salesData);
    }
    
   @FXML
   void GenerateTaxInvoiceButtonClick(ActionEvent event) throws IOException
   {
       //content.getChildren().setAll(FXMLLoader.load(/Users/drashtipanchal/Documents/Drashti/NetBeansProjects/src/erp));
       Parent salesData = FXMLLoader.load(getClass().getResource("InvoiceForm.fxml"));
       contentScrollPane.setContent(salesData);
   }
    
    @FXML
    void GenerateChallanButtonClick(ActionEvent event) throws IOException 
    {
         //Parent salesData = FXMLLoader.load(getClass().getResource("SalesContactDetails.fxml"));
         Parent salesData = FXMLLoader.load(getClass().getResource("SalesContacts.fxml"));
         contentScrollPane.setContent(salesData);

    }

    @FXML
    void SalesHistoryButtonClick(ActionEvent event) throws IOException 
    {
         Parent salesData = FXMLLoader.load(getClass().getResource("SalesDataForm.fxml"));
        contentScrollPane.setContent(salesData);
    }
    
    @FXML
    void GoToDashboardButtonClick(ActionEvent event) throws IOException 
    {
         ReusableCode lc = new ReusableCode();
        lc.GoToDashboardButtonClick(GoToDashboardButton);
    }
    @FXML
    void LogoutButtonClicked(ActionEvent e) 
    {   
        ReusableCode lc = new ReusableCode();
        lc.LogoutButtonClicked(SalesMenuButton);
    }
    
    @FXML
    void SalesContactsButtonClicked(ActionEvent event) throws IOException 
    {
        Parent salesData = FXMLLoader.load(getClass().getResource("SalesContactDetails.fxml"));
        contentScrollPane.setContent(salesData);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //header.getStyleClass().add("headerDashboard");
        //header.getStylesheets().add(BorderPaneDashboardController.class.getResource("header.css").toExternalForm());
        // TODO
    }    
    
}
