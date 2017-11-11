/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class dashboardDrawerController {
    
    @FXML 
    private Button SalesButton;
    
    public void initialize(URL url, ResourceBundle rb) 
    {
       
    }    
    
    @FXML
    void SalesButtonClicked(ActionEvent event) throws IOException 
    {
            Parent openSalesDataForm = FXMLLoader.load(getClass().getResource("SalesDataForm.fxml"));
            Scene SalesDataFormScene = new Scene(openSalesDataForm);
            Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_Stage.setScene(SalesDataFormScene);
            
            //Add stylesheet code
           // SalesDataFormScene.getStylesheets().add(DashboardController.class.getResource("sampleCSS2.css").toExternalForm());
            //app_Stage.setFullScreen(true);
            app_Stage.setMaximized(true);
            //ResponsiveHandler.addResponsiveToWindow(app_Stage);
            app_Stage.show();
    }
    
}
