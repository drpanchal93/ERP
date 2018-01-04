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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class PurchaseDashboardController extends ReusableCode implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private MenuButton PurchaseMenuButton;

    @FXML
    private MenuItem PurchaseProfile;

    @FXML
    private MenuItem PurchaseLogout;
    
     @FXML
    private Button GoToDashboardButton;
     
     @FXML
    private JFXButton AddVendorContactButton;
     
     @FXML
    private AnchorPane contentAnchorPane;

    @FXML
    private ScrollPane contentScrollPane;
    
     @FXML
    void AddVendorContactButtonClicked(ActionEvent event) throws IOException 
    {
        Parent purchaseData = FXMLLoader.load(getClass().getResource("PurchaseContactDetails.fxml"));
         contentScrollPane.setContent(purchaseData);
    }

    @FXML
    void GoToDashboardButtonClick(ActionEvent event) 
    {
        ReusableCode lc = new ReusableCode();
        lc.GoToDashboardButtonClick(GoToDashboardButton);
    }

    @FXML
    void LogoutButtonClicked(ActionEvent e) 
    {
        ReusableCode lc = new ReusableCode();
        lc.LogoutButtonClicked(PurchaseMenuButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
