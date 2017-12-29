/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author drashtipanchal
 */
public class SalesContactDetailsFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML private TextField custName;
    
    @FXML private TextField addressLine1;
    
    @FXML private TextField addressLine2;
    
    @FXML private TextField pCode;
    
    @FXML private TextField ctPersonName;
    
    @FXML private TextField ctNo;
    
    @FXML private TextField eId;
    
    @FXML private TextField gstNo;
    
    @FXML private TextField panNo;
    
    @FXML private TableColumn<SalesContactDetails,String> Name;
    
    @FXML private TableColumn<SalesContactDetails,String> Email;
    
    @FXML private TableColumn<SalesContactDetails,String> Phone;
    
    @FXML private TableView<SalesContactDetails> SalesContactDetailsTable;
    
    public ObservableList<Location> countryList = FXCollections.observableArrayList();
    
    public ObservableList<Location> stateList = FXCollections.observableArrayList();
    
    public ObservableList<Location> cityList = FXCollections.observableArrayList();
    
    @FXML private ComboBox<Location> ctry;
    
    @FXML private ComboBox<Location> st;
    
    @FXML private ComboBox<Location> cty;
    
    
    
    
    @FXML void addButtonClicked(ActionEvent event) 
    {
        TextField contact = new TextField();
    }
    
    Connection conn = DBConnection.democonnection();
    
    @FXML
    void SubmitButtonClicked(ActionEvent event)
    {
        try
        {
            // create a mysql database connection
            
            
            
            // Insert data into Customer Info table

            // the mysql insert statement

            String query = " insert into CustomerInfo (customerName,addLine1 , addLine2, Country, State,City,PinCode,GSTIN,PAN)"

              + " values (?,?,?,?,?,?,?,?,?)";


            // create the mysql insert preparedstatement

            PreparedStatement preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, custName.getText());

            preparedStmt.setString (2, addressLine1.getText());

            preparedStmt.setString (3, addressLine2.getText());

            int pCode_int = new Integer(pCode.getText());
            preparedStmt.setInt (7, pCode_int);

            preparedStmt.setString(8, gstNo.getText());

            preparedStmt.setString(9, panNo.getText());

            // execute the preparedstatement

            ResultSet rs = preparedStmt.executeQuery();
            int customerId = 0;

            while(rs.next()){  
              customerId = rs.getInt(1);  
            }

            // Insert data into Contact Person table

            query = " insert into ContactPerson (customerName, customerId)"

              + " values (?,?)";

            // create the mysql insert preparedstatement

            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, ctPersonName.getText());

            preparedStmt.setInt (2, customerId);

            // execute the preparedstatement

            rs = preparedStmt.executeQuery();
            int personId = 0;

            while(rs.next()){  
              personId = rs.getInt(1);  
            }

            // Insert data into Email table

            query = " insert into email (emailId, personId)"

              + " values (?,?)";

            // create the mysql insert preparedstatement

            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, eId.getText());

            preparedStmt.setInt (2, personId);

            // execute the preparedstatement

            preparedStmt.execute();

            // Insert data into Phone table

            query = " insert into phone (phone, personId)"

              + " values (?,?)";

            // create the mysql insert preparedstatement

            preparedStmt = conn.prepareStatement(query);

            preparedStmt.setString (1, ctNo.getText());

            preparedStmt.setInt (2, personId);

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
    //User should enter the data and dont know how to implement that
    public ObservableList<SalesContactDetails> ciList = FXCollections.observableArrayList();
    
    public void changeNameEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setName((String) editedCell.getNewValue());
    }
    
    public void changeEmailEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setEmail((String) editedCell.getNewValue());
    }
    
    public void changePhoneEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setPhone((String) editedCell.getNewValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        Name.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Name"));
        Email.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Email"));
        Phone.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Phone"));
        
        
        SalesContactDetailsTable.setItems(ciList);
        
        SalesContactDetailsTable.setEditable(true);
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Phone.setCellFactory(TextFieldTableCell.forTableColumn());
        
        //Populating Country ComboBox
        
        // Insert data into Contact Person table

           String  query = "select * from location where location_type = 0";

            // create the mysql insert preparedstatement

           PreparedStatement preparedStmt;
            try 
            {
                preparedStmt = conn.prepareStatement(query);
                ResultSet rs = preparedStmt.executeQuery();

                while(rs.next())
                {  
                    countryList.add(new Location(Integer.parseInt(rs.getString("location_id")), rs.getString("name")));
                    //System.out.println(rs.getString("name"));
                    
                }
                ctry.setItems(countryList);
                
                ctry.setConverter(new StringConverter<Location>() {

                    @Override
                    public String toString(Location object) {
                        return object.getName();
                    }

                    @Override
                    public Location fromString(String string) {
                        return ctry.getItems().stream().filter(ap -> 
                            ap.getName().equals(string)).findFirst().orElse(null);
                    }
                });
                preparedStmt.close();
                rs.close();
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }

            ctry.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    
                    Location newLocation = (Location) newValue;
                    
                    String  query = "select * from location where location_type = 1 AND parent_id = " +newLocation.getId();

                    // create the mysql insert preparedstatement

                   PreparedStatement preparedStmt;
                    try 
                    {
                        preparedStmt = conn.prepareStatement(query);
                        ResultSet rs = preparedStmt.executeQuery();

                        while(rs.next())
                        {  
                            stateList.add(new Location(Integer.parseInt(rs.getString("location_id")), rs.getString("name")));
                            //System.out.println(rs.getString("name"));

                        }
                        
                        stateList.clear();
                        st.setItems(stateList);

                        st.setConverter(new StringConverter<Location>() {

                            @Override
                            public String toString(Location object) {
                                return object.getName();
                            }

                            @Override
                            public Location fromString(String string) {
                                return st.getItems().stream().filter(ap -> 
                                    ap.getName().equals(string)).findFirst().orElse(null);
                            }
                        });
                        preparedStmt.close();
                        rs.close();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
            });
             
             st.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    
                    Location newLocation = (Location) newValue;
                    
                    String  query = "select * from location where location_type = 2 AND parent_id = " +newLocation.getId();

                    // create the mysql insert preparedstatement

                   PreparedStatement preparedStmt;
                    try 
                    {
                        preparedStmt = conn.prepareStatement(query);
                        ResultSet rs = preparedStmt.executeQuery();

                        while(rs.next())
                        {  
                            cityList.add(new Location(Integer.parseInt(rs.getString("location_id")), rs.getString("name")));
                            //System.out.println(rs.getString("name"));

                        }
                        
                        cityList.clear();
                        cty.setItems(cityList);

                        cty.setConverter(new StringConverter<Location>() {

                            @Override
                            public String toString(Location object) {
                                return object.getName();
                            }

                            @Override
                            public Location fromString(String string) {
                                return cty.getItems().stream().filter(ap -> 
                                    ap.getName().equals(string)).findFirst().orElse(null);
                            }
                        });
                        preparedStmt.close();
                        rs.close();
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }    
            });
             
            
    }    
        
      
    /**
     * This method will remove the selected row(s) from the table 
     */
//    public void deleteButtonPushed()
//    {
//        ObservableList<SJO> selectedRows, allItems;
//        allItems = SJOTable.getItems();
//        
//        //this gives us the rows that were selected
//        selectedRows = SJOTable.getSelectionModel().getSelectedItems();
//        
//        //loop over the selected rows and remove the Person objects from the table
//        for (SJO item: selectedRows)
//        {
//            allItems.remove(item);
//            count--;
//            int c = 1;
//            for (SJO items: allItems)
//            {
//                items.setSrNo(c);
//                c++;
//            }
//        }
//    }
//    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */
    public void addButtonPushed()
    {
       
        SalesContactDetails record = new SalesContactDetails(ctPersonName.getText(), eId.getText(), ctNo.getText());
        
        SalesContactDetailsTable.getItems().add(record);
    }
    
}
