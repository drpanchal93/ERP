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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class SalesDashboardController implements Initializable {

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
    private JFXButton SalesButton;
    
    @FXML
    private JFXButton PurchaseButton;
    
    @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    private ScrollPane contentScrollPane;
    
    @FXML
    void SalesButtonClick(ActionEvent event) throws IOException
    {
        //content.getChildren().setAll(FXMLLoader.load(/Users/drashtipanchal/Documents/Drashti/NetBeansProjects/src/erp));
        Parent salesData = FXMLLoader.load(getClass().getResource("SalesDataForm.fxml"));
        contentScrollPane.setContent(salesData);
    }
    
    @FXML
    void PurchaseButtonClick(ActionEvent event) throws IOException
    {
        //content.getChildren().setAll(FXMLLoader.load(/Users/drashtipanchal/Documents/Drashti/NetBeansProjects/src/erp));
        Parent salesData = FXMLLoader.load(getClass().getResource("PurchaseDataForm.fxml"));
        contentScrollPane.setContent(salesData);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //header.getStyleClass().add("headerDashboard");
        //header.getStylesheets().add(BorderPaneDashboardController.class.getResource("header.css").toExternalForm());
        // TODO
    }    
    
}
