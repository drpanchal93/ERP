/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class ReusableCode {
 
    public void LogoutButtonClicked(MenuButton logoutButton)
    {
        Parent goToSceneTwo = null;
            try 
            {
                goToSceneTwo = FXMLLoader.load(getClass().getResource("login.fxml"));
            } catch (IOException ex) 
            {
                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene QualityDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) logoutButton.getScene().getWindow() ;
            app_Stage.setScene(QualityDashboard);
            
            //Css code
            //QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("qualitydashboard.css").toExternalForm());
            
            app_Stage.setMaximized(true);
            app_Stage.show();
    }
    
    public void GoToDashboardButtonClick(Button goToDashboardButton)
    {
        Parent goToSceneTwo = null;
            try 
            {
                goToSceneTwo = FXMLLoader.load(getClass().getResource("DashboardFXML.fxml"));
            } catch (IOException ex) 
            {
                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene QualityDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) goToDashboardButton.getScene().getWindow() ;
            app_Stage.setScene(QualityDashboard);
            
            //Css code
            QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("DashboardFXML.css").toExternalForm());
            
            app_Stage.setMaximized(true);
            app_Stage.show();
    }
}
