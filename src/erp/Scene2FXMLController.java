/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.sun.javafx.stage.StageHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.Parent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class Scene2FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXHamburger hamburgerMenu;

    @FXML
    private JFXDrawer drawer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
        // VBox box = FXMLLoader.load(StageHelper.getStages().getClass().getResource("drawerContent.fxml"));
        StackPane leftDrawerPane = new StackPane();
        leftDrawerPane.getStyleClass().add("deep-purple-400");
        leftDrawerPane.getChildren().add(new JFXButton("Bottom Content"));
        try {
            leftDrawerPane = FXMLLoader.load(StageHelper.getStages().getClass().getResource("drawerContent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Scene2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        drawer.setDefaultDrawerSize(150);
        drawer.setDirection(DrawerDirection.LEFT);
        drawer.setSidePane(leftDrawerPane);
        drawer.setOverLayVisible(false);
        drawer.setResizableOnDrag(true);
        drawer.setSidePane(leftDrawerPane);

        HamburgerBasicCloseTransition t1 = new HamburgerBasicCloseTransition(hamburgerMenu);
        t1.setRate(-1);
        hamburgerMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e
                -> {
            t1.setRate(t1.getRate() * -1);
            t1.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                // drawer.setSidePane(box);

                drawer.open();
            }

        }
        );
    }

}
