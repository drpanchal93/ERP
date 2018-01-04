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
import java.sql.Statement;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
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
    
    @FXML private Label custNameAlert;
    
    @FXML private TextField addressLine1;
    
    @FXML private TextField addressLine2;
    
    @FXML private TextField pCode;
    
    @FXML private Label pCodeAlert;
    
    @FXML private TextField ctPersonName;
    
    @FXML private TextField ctNo;
    
    @FXML private Label ctNoAlert;
    
    @FXML private TextField eId;
    
    @FXML private Label eIdAlert;
    
    @FXML private Label eIdLabel;
    
    @FXML private TextField gstNo;
    
    @FXML private TextField panNo;
    
    @FXML
    private TableColumn<SalesContactDetails, Integer> SrNo;
    
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
    

    Connection conn = DBConnection.democonnection();
    
    @FXML
    void SubmitButtonClicked(ActionEvent event)
    {
        
        boolean flag = true;
        
        if (custName.getText().length() <= 0) {
            flag = false;
            custNameAlert.setVisible(true);
        }
        
        if (pCode.getText().length() > 0) {
            if (!pCode.getText().matches("[0-9]+")) {
                flag = false;
                pCodeAlert.setVisible(true);
            }
        }
        
        if (flag) {
            
            try
            {
                // create a mysql database connection



                // Insert data into Customer Info table

                // the mysql insert statement

                String query = " insert into CustomerInfo (customerName,addLine1,addLine2,locationId,PinCode,GSTIN,PAN)"

                  + " values (?,?,?,?,?,?,?)";


                // create the mysql insert preparedstatement

                PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                preparedStmt.setString (1, custName.getText());

                preparedStmt.setString (2, addressLine1.getText());

                preparedStmt.setString (3, addressLine2.getText());

                preparedStmt.setInt (4, cty.getValue().getId());

                int pCode_int = new Integer(pCode.getText());
                preparedStmt.setInt (5, pCode_int);

                preparedStmt.setString(6, gstNo.getText());

                preparedStmt.setString(7, panNo.getText());

                // execute the preparedstatement

                int rs_int = preparedStmt.executeUpdate();

                if (rs_int == 0) {
                    throw new SQLException("Creating contact failed, no rows affected.");
                }

                try (ResultSet generatedKeys = preparedStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        final int customerId = generatedKeys.getInt(1);


                        SalesContactDetailsTable.getItems().forEach((SalesContactDetails contactPerson) -> {


                            // Insert data into Contact Person table

                            String query1 = " insert into ContactPersonInfo (contactPersonName, custInfoId)"

                              + " values (?,?)";


                            try {
                                // create the mysql insert preparedstatement

                                PreparedStatement preparedStmt1 = conn.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS);
                                preparedStmt1.setString (1, contactPerson.getName());

                                preparedStmt1.setInt (2, customerId);

                                // execute the preparedstatement

                                int rs1_int = preparedStmt1.executeUpdate();

                                if (rs1_int == 0) {
                                    throw new SQLException("Creating person failed, no rows affected.");
                                }

                                try (ResultSet generatedKeys1 = preparedStmt1.getGeneratedKeys()) {
                                    if (generatedKeys1.next()) {
                                        int personId = generatedKeys1.getInt(1);
                                        // Insert data into Email table

                                        String[] email = contactPerson.getEmail().split(",");

                                        for(int i=0; i<email.length; i++) {
                                            String query2 = " insert into EmailInfo (emailAddress, contactPersonId)"

                                              + " values (?,?)";

                                            // create the mysql insert preparedstatement

                                            try {
                                                PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
                                                preparedStmt2.setString (1, email[i]);

                                                preparedStmt2.setInt (2, personId);

                                                // execute the preparedstatement

                                                preparedStmt2.execute();
                                            } catch (SQLException ex) {
                                                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                            }


                                        }

                                        // Insert data into Phone table

                                        String[] phone = contactPerson.getPhone().split(",");

                                        for(int i=0; i<phone.length; i++) {
                                            String query3 = " insert into ContactNumberInfo (contactNumber, contactPersonId)"

                                              + " values (?,?)";

                                            // create the mysql insert preparedstatement

                                            try {
                                                PreparedStatement preparedStmt3 = conn.prepareStatement(query3);

                                                preparedStmt3.setString (1, phone[i]);

                                                preparedStmt3.setInt (2, personId);

                                                // execute the preparedstatement

                                                preparedStmt3.execute();
                                            } catch (SQLException ex) {
                                                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                    }
                                    else {
                                        throw new SQLException("Creating person failed, no ID obtained.");
                                    }
                                }

                            } catch (SQLException ex) {
                                Logger.getLogger(SalesContactDetailsFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                            }



                        });
                    }
                    else {
                        throw new SQLException("Creating contact failed, no ID obtained.");
                    }
                }

                custName.clear();
                addressLine1.clear();
                addressLine2.clear();
                pCode.clear();
                gstNo.clear();
                panNo.clear();
                

            }

            catch (Exception e)

            {

              System.err.println("Got an exception!");

              System.err.println(e.getMessage());
              System.err.println(e);

            }
        }
    }
    //User should enter the data and dont know how to implement that
    public ObservableList<SalesContactDetails> ciList = FXCollections.observableArrayList();
    
    public void changeSrNoCellEvent(TableColumn.CellEditEvent editedCell)
    {
        SalesContactDetails Selected =  SalesContactDetailsTable.getSelectionModel().getSelectedItem();
        Selected.setSrNo((Integer) editedCell.getNewValue());
    }
    
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
    
    int count = 1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        SrNo.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, Integer>("SrNo"));
        Name.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Name"));
        Email.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Email"));
        Phone.setCellValueFactory(new PropertyValueFactory<SalesContactDetails, String>("Phone"));
        
        
        SalesContactDetailsTable.setItems(ciList);
        
        SalesContactDetailsTable.setEditable(true);
        SrNo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        Name.setCellFactory(TextFieldTableCell.forTableColumn());
        Email.setCellFactory(TextFieldTableCell.forTableColumn());
        Phone.setCellFactory(TextFieldTableCell.forTableColumn());
        
        // Hide Alerts
        custNameAlert.setVisible(false);
        pCodeAlert.setVisible(false);
        ctNoAlert.setVisible(false);
        eIdAlert.setVisible(false);
        
        //Populating Country ComboBox
        
        // Insert data into Contact Person table

           String  query = "select * from location where location_type = 0 order by name";

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
                    System.out.println(newLocation.getId());
                    
                    stateList.clear();
                        
                    String  query = "select * from location where location_type = 1 AND parent_id = " +newLocation.getId() + "  order by name";

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
                    
                    cityList.clear();
                    
                    String  query = "select * from location where location_type = 2 AND parent_id = " +newLocation.getId() + "  order by name";

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
             
            
        
        custName.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent arg0) {
                    TextField customer_name = (TextField) arg0.getSource();
                    int length = customer_name.getText().length();
                    
                    if (length <= 0) {
                        custNameAlert.setVisible(true);
                    }
                    else {
                        custNameAlert.setVisible(false);
                    }
                }
        });
        
        pCode.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent arg0) {
                    TextField pin_code = (TextField) arg0.getSource();
                    int length = pin_code.getText().length();
                    
                    if (length > 0 && !pin_code.getText().matches("[0-9]+")) {
                        pCodeAlert.setVisible(true);
                    }
                    else {
                        pCodeAlert.setVisible(false);
                    }
                }
        });
        
        ctNo.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent arg0) {
                    TextField contact_no = (TextField) arg0.getSource();
                    int length = contact_no.getText().length();
                    
                    if (length > 0 && !contact_no.getText().matches("[0-9]+")) {
                        ctNoAlert.setVisible(true);
                    }
                    else {
                        ctNoAlert.setVisible(false);
                    }
                }
        });
        
        /*eId.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent arg0) {
                    TextField email = (TextField) arg0.getSource();
                    int length = email.getText().length();
                    Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
                    Matcher matcher = pattern.matcher(email.getText());
                    
                    if (length > 0 && !matcher.matches()) {
                        eIdAlert.setVisible(true);
                    }
                    else {
                        eIdAlert.setVisible(false);
                    }
                }
        });*/
            
    }    
        
      
    /**
     * This method will remove the selected row(s) from the table 
     */
    public void deleteButtonPushed()
   {
       
        ObservableList<SalesContactDetails> selectedRows, allItems;
        allItems = SalesContactDetailsTable.getItems();
        
        //this gives us the rows that were selected
        selectedRows = SalesContactDetailsTable.getSelectionModel().getSelectedItems();
        
        //loop over the selected rows and remove the Person objects from the table
        for (SalesContactDetails item: selectedRows)
        {
            allItems.remove(item);
            count--;
            int c = 1;
            for (SalesContactDetails items: allItems)
            {
                items.setSrNo(c);
                c++;
            }
        }
    }
    
    
    
    /**
     * This method will create a new Person object and add it to the table
     */
    public void addButtonPushed()
    {
        SalesContactDetails record = new SalesContactDetails(count,ctPersonName.getText(), eId.getText(), ctNo.getText());
        boolean flag = true;
        count++;
        
        if (ctNo.getText().length() > 0) {
            if (!ctNo.getText().matches("[0-9]+")) {
                flag = false;
                ctNoAlert.setVisible(true);
            }
        }
        
        Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
        Matcher matcher = pattern.matcher(eId.getText());

        /*if (eId.getText().length() > 0) {
            if (!matcher.matches()) {
                flag = false;
                eIdAlert.setVisible(true);
            }
        }*/
        if (flag) {
            SalesContactDetailsTable.getItems().add(record);
            ctPersonName.clear();
            eId.clear();
            ctNo.clear();
        }
    }
   
}
