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
public class StoreDashboardController extends ReusableCode implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private MenuButton StoreMenuButton;

    @FXML
    private MenuItem StoreViewProfile;

    @FXML
    private MenuItem StoreLogout;
    
      @FXML
    private Button GoToDashboardStoreButton;

    @FXML
    private FontAwesomeIconView GoToDashboardStoreIcon;

    @FXML
    void GoToDashboardStoreButtonClick(Event event) 
    {
        ReusableCode lc = new ReusableCode();
        lc.GoToDashboardButtonClick(GoToDashboardStoreButton);
    }

    @FXML
    void LogoutButtonClicked(ActionEvent event) {
        ReusableCode lc = new ReusableCode();
        lc.LogoutButtonClicked(StoreMenuButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         GoToDashboardStoreIcon.setPickOnBounds(true); // allows click on transparent areas
        GoToDashboardStoreIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Clicked!"); // change functionality
            GoToDashboardStoreButtonClick(e);
        });
    }    
    
}
