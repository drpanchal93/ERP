/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ComboBox<String> profileMenu;
    
     public ObservableList<String> profileMenuList = FXCollections.observableArrayList();
//     @FXML
//    private Circle userProfileImage;
    
//    /*-----------------------------------------------------*/
//     //SalesButton
    @FXML
    private Button SalesButton;
    //      
//    @FXML
//    private ImageView testImage;
//    
    @FXML
    void SalesButtonClick(ActionEvent event) throws IOException {
        OpenSalesDashboard(event);
    }
    
    void OpenSalesDashboard(Event e) 
    {
        Parent goToSceneTwo = null;
            try 
            {
                //goToSceneTwo = FXMLLoader.load(getClass().getResource("SalesDashboard.fxml"));
                goToSceneTwo = FXMLLoader.load(getClass().getResource("SalesDashboardResponsive.fxml"));
            } catch (IOException ex) 
            {
                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene SalesDashboard = new Scene(goToSceneTwo);
            Stage app_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            app_Stage.setScene(SalesDashboard);
            
            //Css code
            SalesDashboard.getStylesheets().add(SalesDashboardController.class.getResource("salesdashboard.css").toExternalForm());
            
            app_Stage.setMaximized(true);
            app_Stage.show();
    }
//    /*---------------------------------------------------------------------------------------------------*/
//    //PurchaseButton
//    @FXML
//    private Button PurchaseButton;
//
//    @FXML
//    private ImageView purchaseIcon;
//    
//    @FXML
//    void PurchaseButtonClick(ActionEvent event) throws IOException {
//        OpenPurchaseDashboard(event);
//    }
//    
//    void OpenPurchaseDashboard(Event e)
//    {
//        Parent goToSceneTwo = null;
//            try {
//                goToSceneTwo = FXMLLoader.load(getClass().getResource("PurchaseDashboard.fxml"));
//            } catch (IOException ex) {
//                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Scene PurchaseDashboard = new Scene(goToSceneTwo);
//            Stage app_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//            app_Stage.setScene(PurchaseDashboard);
//            
//            //Css code
//            PurchaseDashboard.getStylesheets().add(PurchaseDashboardController.class.getResource("purchasedashboard.css").toExternalForm());
//            
//            app_Stage.setMaximized(true);
//            app_Stage.show();
//    }
//    /*------------------------------------------------------------------------------------------------------------*/
//    @FXML
//    private Button FinanceButton;
//
//    @FXML
//    private ImageView financeIcon;
//    
//     @FXML
//    void FinanceButtonClick(ActionEvent event) 
//    {
//        OpenFinanceDashboard(event);
//    }
//    
//    void OpenFinanceDashboard(Event e)
//    {
//         Parent goToSceneTwo = null;
//            try 
//            {
//                goToSceneTwo = FXMLLoader.load(getClass().getResource("FinanceDashboard.fxml"));
//            } catch (IOException ex) 
//            {
//                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Scene FinanceDashboard = new Scene(goToSceneTwo);
//            Stage app_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//            app_Stage.setScene(FinanceDashboard);
//            
//            //Css code
//            FinanceDashboard.getStylesheets().add(FinanceDashboardController.class.getResource("financedashboard.css").toExternalForm());
//            
//            app_Stage.setMaximized(true);
//            app_Stage.show();
//    }
//    /*------------------------------------------------------------------------------------------------------------*/
//    //ProductionButton
//    @FXML
//    private Button ProductionButton;
//
//    @FXML
//    private ImageView productionIcon;
//    
//     @FXML
//    void ProductionButtonClick(ActionEvent event) {
//        OpenProductionDashboard(event);
//    }
//    
//    void OpenProductionDashboard(Event e)
//    {
//         Parent goToSceneTwo = null;
//            try 
//            {
//                goToSceneTwo = FXMLLoader.load(getClass().getResource("ProductionDashboard.fxml"));
//            } catch (IOException ex) 
//            {
//                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Scene ProductionDashboard = new Scene(goToSceneTwo);
//            Stage app_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//            app_Stage.setScene(ProductionDashboard);
//            
//            //Css code
//            //ProductionDashboard.getStylesheets().add(ProductionDashboardController.class.getResource("productiondashboard.css").toExternalForm());
//            
//            app_Stage.setMaximized(true);
//            app_Stage.show();
//    }
//    /*-----------------------------------------------------------------------------------------------------------*/
//    //StoreButton
//    
//     @FXML
//    private Button StoreButton;
//     
//    @FXML
//    private ImageView storeIcon;
//
//    @FXML
//    void StoreButtonClick(ActionEvent event) {
//        OpenStoreDashboard(event);
//    }
//    
//    void OpenStoreDashboard(Event e)
//    {
//        Parent goToSceneTwo = null;
//            try 
//            {
//                goToSceneTwo = FXMLLoader.load(getClass().getResource("StoreDashboard.fxml"));
//            } catch (IOException ex) 
//            {
//                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Scene StoreDashboard = new Scene(goToSceneTwo);
//            Stage app_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//            app_Stage.setScene(StoreDashboard);
//            
//            //Css code
//            //StoreDashboard.getStylesheets().add(StoreDashboardController.class.getResource("storedashboard.css").toExternalForm());
//            
//            app_Stage.setMaximized(true);
//            app_Stage.show();
//    }
//    /*---------------------------------------------------------------------------------------------------------*/
//    //QualityButton
//     @FXML
//    private Button QualityButton;
//
//    @FXML
//    private ImageView qualityIcon;
//    
//    @FXML
//    void QualityButtonClick(ActionEvent event) {
//        OpenQualityDashboard(event);
//    }
//    
//    void OpenQualityDashboard(Event e)
//    {
//        Parent goToSceneTwo = null;
//            try 
//            {
//                goToSceneTwo = FXMLLoader.load(getClass().getResource("QualityDashboard.fxml"));
//            } catch (IOException ex) 
//            {
//                Logger.getLogger(DashboardFXMLController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            Scene QualityDashboard = new Scene(goToSceneTwo);
//            Stage app_Stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
//            app_Stage.setScene(QualityDashboard);
//            
//            //Css code
//            QualityDashboard.getStylesheets().add(QualityDashboardController.class.getResource("qualitydashboard.css").toExternalForm());
//            
//            app_Stage.setMaximized(true);
//            app_Stage.show();
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
       /* testImage.setPickOnBounds(true); // allows click on transparent areas
        testImage.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Clicked!"); // change functionality
            OpenSalesDashboard(e);
        });
        
        purchaseIcon.setPickOnBounds(true);
        purchaseIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Purchase Image Clicked");
            OpenPurchaseDashboard(e);
        });
        
        financeIcon.setPickOnBounds(true);
        financeIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Finance Image Clicked");
            OpenFinanceDashboard(e);
        });
        
        productionIcon.setPickOnBounds(true);
        productionIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Production Image Clicked");
            OpenProductionDashboard(e);
        });
        
        storeIcon.setPickOnBounds(true);
        storeIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Store Image Clicked");
            OpenStoreDashboard(e);
        });
        
        qualityIcon.setPickOnBounds(true);
        qualityIcon.setOnMouseClicked((MouseEvent e) -> 
        {
            System.out.println("Quality Image Clicked");
            OpenQualityDashboard(e);
        });*/
        
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
