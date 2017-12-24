/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class SalesContactDetailsFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField custName;
    
    @FXML
    private TextField addressLine1;
    
    @FXML
    private TextField addressLine2;
    
    @FXML
    private TextField ctry;
    
    @FXML
    private TextField st;
    
    @FXML
    private TextField cty;
    
    @FXML
    private TextField pCode;
    
    @FXML
    private TextField ctPersonName;
    
    @FXML
    private TextField ctNo1;
    
    @FXML
    private TextField ctNo2;
    
    @FXML
    private TextField eId;
    
    @FXML
    private TextField gstNo;
    
    @FXML
    private TextField panNo;
    
    @FXML
    void SubmitButtonClicked(ActionEvent event)
    {
        try
        {
            // create a mysql database connection
            Connection conn = DBConnection.democonnection();

      // the mysql insert statement

      String query = " insert into CustomerInfo (customerName,addLine1 , addLine2, Country, State,City,PinCode,contPersonName,contNumber1,contNumber2,emailId,GSTIN,PAN)"

        + " values (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";



      // create the mysql insert preparedstatement

      PreparedStatement preparedStmt = conn.prepareStatement(query);

      preparedStmt.setString (1, custName.getText());

      preparedStmt.setString (2, addressLine1.getText());

      preparedStmt.setString (3, addressLine2.getText());

      preparedStmt.setString (4, ctry.getText());

      preparedStmt.setString (5, st.getText());
      
      preparedStmt.setString (6, cty.getText());
      
      int pCode_int = new Integer(pCode.getText());
      preparedStmt.setInt (7, pCode_int);
      
      preparedStmt.setString(8, ctPersonName.getText());
      
      int ctNo1_int = new Integer(ctNo1.getText());
      preparedStmt.setInt(9, ctNo1_int);
      
      int ctNo2_int = new Integer(ctNo2.getText());
      preparedStmt.setInt(10,ctNo2_int);
      
      preparedStmt.setString(11, eId.getText());
 
      preparedStmt.setString(12, gstNo.getText());

      preparedStmt.setString(13, panNo.getText());

      // execute the preparedstatement

      preparedStmt.execute();

      

      conn.close();

    }

    catch (Exception e)

    {

      System.err.println("Got an exception!");

      System.err.println(e.getMessage());

    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    } 
    /*
    try

    {

      // create a mysql database connection

      Connection conn = DBConnection.democonnection();

      // the mysql insert statement

      String query = " insert into users (column1, column2, column3, column4, column5)"

        + " values (?, ?, ?, ?, ?)";



      // create the mysql insert preparedstatement

      PreparedStatement preparedStmt = conn.prepareStatement(query);

      preparedStmt.setString (1, value1.getText());

      preparedStmt.setString (2, value2.getText());

      preparedStmt.setDate   (3, value3.getText());

      preparedStmt.setBoolean(4, value4.getText());

   

     //For int values   

      String value5 = value5.getText();

      int value5_int = new Integer(value5);

    

      preparedStmt.setInt    (5, value5_int);

      // execute the preparedstatement

      preparedStmt.execute();

      

      conn.close();

    }

    catch (Exception e)

    {

      System.err.println("Got an exception!");

      System.err.println(e.getMessage());

    }
    */
    
}
