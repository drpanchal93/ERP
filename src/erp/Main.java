/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author admin
 */
public class Main extends Application {
    
    Stage window;
    Scene scene1;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        
        Parent root;
        //Parent Sc2 = FXMLLoader.load(getClass().getResource("Scene2FXML.fxml"));
        root = FXMLLoader.load(getClass().getResource("SalesDataForm.fxml"));
        
        scene1 = new Scene(root);
        //scene2 = new Scene(Sc2);
        window.setMaximized(true);
        window.setScene(scene1);
        window.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
