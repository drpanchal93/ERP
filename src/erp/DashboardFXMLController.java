/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chaitya
 */
public class DashboardFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
     @FXML
    private MenuButton profileButton;
     
     @FXML
    private Circle userProfileImage;
    
     
    @FXML
    void SalesButtonClick(ActionEvent event) throws IOException {
        Parent goToSceneTwo = FXMLLoader.load(getClass().getResource("SalesDashboard.fxml"));
            Scene SalesDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_Stage.setScene(SalesDashboard);
            //Css code
            //SalesDashboard.getStylesheets().add(DashboardController.class.getResource("salesdashboard.css").toExternalForm());
            app_Stage.setMaximized(true);
            app_Stage.show();
    }
    // profileButton.setGraphic(profileButton)
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
