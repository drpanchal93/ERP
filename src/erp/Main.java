/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author admin
 */
public class Main extends Application {
    
    Stage window;
    Scene scene1;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
       window = primaryStage;
        
       Parent root;
        //Parent Sc2 = FXMLLoader.load(getClass().getResource("Scene2FXML.fxml"));
       root = FXMLLoader.load(getClass().getResource("LoginResponsive.fxml"));
       //root = FXMLLoader.load(getClass().getResource("Sample1.fxml"));
      /* FXMLLoader loader = new FXMLLoader(getClass().getResource("SampleDrawerMenu.fxml"));
       loader.setController(new SampleDrawerMenuController());
       Pane mainPane = loader.load();*/
        
        scene1 = new Scene(root);
        //scene2 = new Scene(Sc2);
                
       // scene1.getStylesheets().add(loginController.class.getResource("allFooter.css").toExternalForm());
        window.setMaximized(true);
        window.setScene(scene1);
        window.show();
       
        /*Button btn = new Button();
        btn.setText("File");

        final StackPane root = new StackPane();
        AnchorPane editorRoot = new AnchorPane();
        editorRoot.getChildren().add(btn);
        root.getChildren().add(editorRoot);

        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add("E:/NetbeansProjects/ERP/src/erp/css");

        primaryStage.setScene(scene);
        primaryStage.show();

        HBox fileRoot = new HBox();
        VBox menu = new VBox();
        menu.setStyle("-fx-background-color: blue;");
        menu.setFillWidth(true);
        Button backBtn = new Button("Left Arrow");
        backBtn.setPrefWidth(100);
        backBtn.getStyleClass().add("custom-menu-button");
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                FadeTransition hideFileRootTransition = new FadeTransition(Duration.millis(500), fileRoot);
                hideFileRootTransition.setFromValue(1.0);
                hideFileRootTransition.setToValue(0.0);

                FadeTransition showEditorRootTransition = new FadeTransition(Duration.millis(500), editorRoot);
                showEditorRootTransition.setFromValue(0.0);
                showEditorRootTransition.setToValue(1.0);

                showEditorRootTransition.play();
                hideFileRootTransition.play();
                root.getChildren().remove(fileRoot);
            }
        });
        Button infoBtn = new Button("Info");
        infoBtn.setPrefWidth(100);
        infoBtn.getStyleClass().add("custom-menu-button");
        Button newBtn = new Button("New");
        newBtn.setPrefWidth(100);
        newBtn.getStyleClass().add("custom-menu-button");
        Button openBtn = new Button("Open");
        openBtn.setPrefWidth(100);
        openBtn.getStyleClass().add("custom-menu-button");
        menu.getChildren().addAll(backBtn,infoBtn, newBtn, openBtn);
        VBox.setVgrow(infoBtn, Priority.ALWAYS);
        fileRoot.getChildren().add(menu);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                root.getChildren().add(fileRoot);
                FadeTransition hideEditorRootTransition = new FadeTransition(Duration.millis(500), editorRoot);
                hideEditorRootTransition.setFromValue(1.0);
                hideEditorRootTransition.setToValue(0.0);

                FadeTransition showFileRootTransition = new FadeTransition(Duration.millis(500), fileRoot);
                showFileRootTransition.setFromValue(0.0);
                showFileRootTransition.setToValue(1.0);
                hideEditorRootTransition.play();
                showFileRootTransition.play();
            }
        });*/
    }
    
    
     /* @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
