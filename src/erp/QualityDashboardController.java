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
public class QualityDashboardController extends ReusableCode implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MenuButton QualityMenuButton;

    @FXML
    private MenuItem QualityViewProfile;

    @FXML
    private MenuItem QualityLogout;
    
    @FXML
    private Button GoToDashboardQualityButton;

    @FXML
    private FontAwesomeIconView GoToDashboardQualityIcon;

    @FXML
    void GoToDashboardQualityButtonClick(Event event) 
    {
        ReusableCode lc = new ReusableCode();
        lc.GoToDashboardButtonClick(GoToDashboardQualityButton);
    }

    @FXML
    void LogoutButtonClicked(ActionEvent event) {
        ReusableCode lc = new ReusableCode();
        lc.LogoutButtonClicked(QualityMenuButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GoToDashboardQualityIcon.setPickOnBounds(true); // allows click on transparent areas
        GoToDashboardQualityIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Clicked!"); // change functionality
            GoToDashboardQualityButtonClick(e);
        });
    }    
    
}
