/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class BorderPaneDashboardController implements Initializable {

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
    void SalesButtonClick(ActionEvent event)
    {
        //content.getChildren().setAll(FXMLLoader.load(/Users/drashtipanchal/Documents/Drashti/NetBeansProjects/src/erp));
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        header.getStyleClass().add("headerDashboard");
        //header.getStylesheets().add(BorderPaneDashboardController.class.getResource("header.css").toExternalForm());
        // TODO
    }    
    
}
