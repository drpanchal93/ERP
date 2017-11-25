/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class StoreDashboardController extends LogoutCode implements Initializable {

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
    void LogoutButtonClicked(ActionEvent event) {
        LogoutCode lc = new LogoutCode();
        lc.LogoutButtonClicked(StoreMenuButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
