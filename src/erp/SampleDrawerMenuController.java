/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class SampleDrawerMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try {
            // TODO
            VBox box = FXMLLoader.load(getClass().getResource("drawerContent.fxml"));
            drawer.setSidePane(box);
            HamburgerBasicCloseTransition task1 = new HamburgerBasicCloseTransition(hamburger);
            task1.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) ->
            {
                task1.setRate(task1.getRate() * -1);
                task1.play();
                if(drawer.isShown())
                {
                    drawer.close();
                }
                else
                {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(SampleDrawerMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
