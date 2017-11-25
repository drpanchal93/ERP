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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class SalesDashboardController extends LogoutCode implements Initializable {

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
    private MenuButton SalesMenuButton;
    
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
    
    @FXML
    void LogoutButtonClicked(ActionEvent e) 
    {
       /* Parent goToSceneTwo = null;
            try 
            {
                goToSceneTwo = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException ex) 
            {
                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene QualityDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) SalesMenuButton.getScene().getWindow() ;
            app_Stage.setScene(QualityDashboard);
            
            //Css code
            //QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("qualitydashboard.css").toExternalForm());
            
            app_Stage.setMaximized(true);
            app_Stage.show();*/
        
        LogoutCode lc = new LogoutCode();
        lc.LogoutButtonClicked(SalesMenuButton);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //header.getStyleClass().add("headerDashboard");
        //header.getStylesheets().add(BorderPaneDashboardController.class.getResource("header.css").toExternalForm());
        // TODO
    }    
    
}
