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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private JFXButton PurchaseContactDetails;
    
    @FXML
    private ComboBox<String> profileMenu;
    
    public ObservableList<String> profileMenuList = FXCollections.observableArrayList();
    
    @FXML
    void PurchaseContactDetailsButtonClicked(ActionEvent event) throws IOException 
    {
        Parent salesData = FXMLLoader.load(getClass().getResource("PurchaseContacts.fxml"));
        contentScrollPane.setContent(salesData);
    }

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
        profileMenuList.add("User Profile");
        profileMenuList.add("Logout");
        
        profileMenu.setItems(profileMenuList);
        
        profileMenu.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    
                    String menuItem = (String) newValue;
                    
                    if (menuItem.equals("Logout")) {
                        Parent goToSceneTwo = null;
                        try 
                        {
                            goToSceneTwo = FXMLLoader.load(getClass().getResource("LoginResponsive.fxml"));
                        } catch (IOException ex) 
                        {
                            Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        Scene QualityDashboard = new Scene(goToSceneTwo);
                        Stage app_Stage = (Stage) profileMenu.getScene().getWindow() ;
                        app_Stage.setScene(QualityDashboard);

                        //Css code
                        //QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("qualitydashboard.css").toExternalForm());

                        app_Stage.setMaximized(true);
                        app_Stage.show();
                    }
                }
        });
    }    
  }    
    

