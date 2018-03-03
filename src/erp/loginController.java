/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 *
 * @author admin
 */
public class loginController implements Initializable 
{
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton submit;
    
   /* @FXML
    private void handleSubmitButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("Submit button clicked");
        Parent goToSceneTwo = FXMLLoader.load(getClass().getResource("SampleDrawerMenu.fxml"));
        Scene SampleDrawerMenuScene = new Scene(goToSceneTwo);
        Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_Stage.setScene(SampleDrawerMenuScene);
        app_Stage.show();
    }*/

    @FXML
    private Label forgot;

    @FXML
    private JFXTextField username;
    
    @FXML
    private Label error;

    /*Stage stage;
    Parent sc2;*/
    //Database Connection
    @FXML
    void loginClicked(ActionEvent event) throws SQLException, IOException 
    {
        //System.out.println("Login button clicked!");
        /*DBConnection db = new DBConnection();
        Connection con = DBConnection.democonnection();
        
        PreparedStatement stmt = con.prepareStatement("select password from signup where username = ?");
        stmt.setString(1, username.getText());
        
        ResultSet rs=stmt.executeQuery(); 
        if(rs.next() && rs.getString(1).equals(password.getText()))
        { 
            System.out.println("Login Successful");  */
            /*stage = (Stage) submit.getScene().getWindow();
            sc2 = FXMLLoader.load(getClass().getResource("Scene2FXML.fxml"));
            Scene scene = new Scene(sc2);
            stage.setScene(scene);
            stage.show();*/
            
            Parent goToSceneTwo = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
            Scene DashboardDrawerMenuScene = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_Stage.setScene(DashboardDrawerMenuScene);
            
            //Add stylesheet code
            DashboardDrawerMenuScene.getStylesheets().add(DashboardController.class.getResource("DashboardFXML.css").toExternalForm());
            
            //app_Stage.setFullScreen(true);
            app_Stage.setMaximized(true);
            //ResponsiveHandler.addResponsiveToWindow(app_Stage);
            app_Stage.show();
        /*}
        else
        {
            System.out.println("Incorrect Username or Password");
            error.setText("Incorrect Username or Password");
        }*/
            
        
        
        
        //window.setScene(scene2);
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //combobox.setItems(list);
    }  
    
    /*@FXML
    void submitClicked(ActionEvent event) throws SQLException 
    {
        //System.out.println("Login button clicked!");
        DBConnection db2 = new DBConnection();
        Connection con2 = DBConnection.democonnection();
        
        PreparedStatement stmt2=con2.prepareStatement("insert into signup values(?,?,?,?,?,?,?,?)");  
        stmt2.setString(1,firstname.getText());
        stmt2.setString(2,lastname.getText());
        stmt2.setString(3, email.getText());
        stmt2.setString(4, phno.getText());
        stmt2.setString(5, username.getText());
        stmt2.setString(6, password.getText());
        stmt2.setString(7, module.getText());
        stmt2.setString(8, combobox.getValue());
        
        int i=stmt2.executeUpdate();  
        
        System.out.println(i+" records inserted");  
        /*while(rs.next()){
            if (rs.getString(1).equals(password.getText()))
            {
                System.out.println("Login Successful");  
            }
            else
            {
                System.out.println("Login Unsuccessful");
            }
        }
    }*/
        
      
}
