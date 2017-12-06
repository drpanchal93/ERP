/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class FinanceDashboardController extends ReusableCode implements Initializable {

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
    private Button GoToDashboardFinanceButton;
    
    @FXML
    private FontAwesomeIconView GoToDashboardFinanceIcon;

    @FXML
    void GoToDashboardFinanceButtonClick(Event event) 
    {
        ReusableCode lc = new ReusableCode();
        lc.GoToDashboardButtonClick(GoToDashboardFinanceButton);
    }

    
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
        ReusableCode lc = new ReusableCode();
        lc.LogoutButtonClicked(FinanceMenuButton);
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GoToDashboardFinanceIcon.setPickOnBounds(true); // allows click on transparent areas
        GoToDashboardFinanceIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Clicked!"); // change functionality
            GoToDashboardFinanceButtonClick(e);
        });
        
    }    
    
}
