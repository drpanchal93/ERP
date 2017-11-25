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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FinanceDashboardController extends LogoutCode implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private MenuButton FinanceMenuButton;

    @FXML
    private MenuItem FinanceProfile;

    @FXML
    private MenuItem FinanceLogout;
    
    @FXML
    void LogoutButtonClicked(ActionEvent e) 
    {
        /*Parent goToSceneTwo = null;
            try 
            {
                goToSceneTwo = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException ex) 
            {
                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene QualityDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) FinanceMenuButton.getScene().getWindow() ;
            app_Stage.setScene(QualityDashboard);
            
            //Css code
            //QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("qualitydashboard.css").toExternalForm());
            
            app_Stage.setMaximized(true);
            app_Stage.show();*/
        LogoutCode lc = new LogoutCode();
        lc.LogoutButtonClicked(FinanceMenuButton);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
}
