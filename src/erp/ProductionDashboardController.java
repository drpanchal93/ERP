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
public class ProductionDashboardController extends ReusableCode implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private MenuButton ProductionMenuButton;

    @FXML
    private MenuItem ProductionViewProfile;

    @FXML
    private MenuItem ProductionLogout;
    
    @FXML
    private Button GoToDashboardProductionButton;

    @FXML
    private FontAwesomeIconView GoToDashboardProductionIcon;

    @FXML
    void GoToDashboardProductionButtonClick(Event event) 
    {
        ReusableCode lc = new ReusableCode();
        lc.GoToDashboardButtonClick(GoToDashboardProductionButton);
    }

    
      @FXML
    void LogoutButtonClicked(ActionEvent event) 
    {
        ReusableCode lc = new ReusableCode();
        lc.LogoutButtonClicked(ProductionMenuButton);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
        GoToDashboardProductionIcon.setPickOnBounds(true); // allows click on transparent areas
        GoToDashboardProductionIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Clicked!"); // change functionality
            GoToDashboardProductionButtonClick(e);
        });
    }    
    
}
