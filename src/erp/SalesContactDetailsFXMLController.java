/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    
    int phoneFields = 1;
    int index = 0;
        
    @FXML
    private GridPane AccordionGridPane;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private TitledPane titledPane;
    
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
    private ComboBox<String> phoneType;
    
    public ObservableList<TextField> ct_No = FXCollections.observableArrayList();
    
    public ObservableList<TextField> ct_areaCode = FXCollections.observableArrayList();
    
    public ObservableList<ComboBox> phone_Type = FXCollections.observableArrayList();
    
    public ObservableList<ComboBox> country_code = FXCollections.observableArrayList();
    
    public ObservableList<String> phoneTypeList = FXCollections.observableArrayList();
    
    @FXML
    private Button addPhone;
    
    @FXML
    private TextField ctAreaCode;
    
    @FXML
    private TableColumn<SalesContactDetails, Integer> SrNo;
    
    @FXML private TableColumn<SalesContactDetails,String> Name;
    
    @FXML private TableColumn<SalesContactDetails,String> Email;
    
    @FXML private TableColumn<SalesContactDetails,String> Phone;
    
    @FXML private TableView<SalesContactDetails> SalesContactDetailsTable;
    
    public ObservableList<Location> countryList = FXCollections.observableArrayList();
    
    public ObservableList<Location> stateList = FXCollections.observableArrayList();
    
    public ObservableList<Location> cityList = FXCollections.observableArrayList();
    
    public ObservableList<Location> countryCodeList = FXCollections.observableArrayList();
    
    @FXML private ComboBox<Location> ctry;
    
    @FXML private ComboBox<Location> st;
    
    @FXML private ComboBox<Location> cty;
    
    @FXML private ComboBox<Location> cc;
    
    @FXML
    private AnchorPane salesContactFormAnchorPane;
    
    @FXML
    private JFXButton goBackToSalesContacts;
            
    Connection conn = DBConnection.democonnection();
    
    @FXML
    void goBackToSalesContactsButtonCLicked(ActionEvent event) throws IOException 
    {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SalesContacts.fxml"));
        salesContactFormAnchorPane.getChildren().setAll(pane);
    }
    
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
                                              
                                            String temp = phone[i];
                                            String[] ctc = temp.split("-");
                                            String[] ph = ctc[2].split("\\(");
                                            String types = "Work";
                                            if(ph[1].charAt(0) == 'M') {
                                                types = "Mobile";
                                            }
                                            else if(ph[1].charAt(0) == 'H') {
                                                types = "Home";
                                            }
                                            String query3 = "insert into ContactNumberInfo (contactNumber, contactPersonId, contactNumberType,numberAreaCode,countryCode)"

                                            + " values (?,?,?,?,?)";

                                            // create the mysql insert preparedstatement

                                            try {
                                                PreparedStatement preparedStmt3 = conn.prepareStatement(query3);

                                                preparedStmt3.setString (1, ph[0]);

                                                preparedStmt3.setInt (2, personId);

                                                preparedStmt3.setString (3, types);
                                                
                                                preparedStmt3.setString(4, ctc[1]);
                                                
                                                preparedStmt3.setString (5, ctc[0]);

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

              System.err.println("Got an exception in SalesFXMLController!");

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
        
        // Set Items to combolist phoneType
        phoneTypeList.add("Work");
        phoneTypeList.add("Mobile");
        phoneTypeList.add("Home");
        
        phoneType.setItems(phoneTypeList);
        
        
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
            
            String  query1 = "select * from countryCode";

            // create the mysql insert preparedstatement

           PreparedStatement preparedStmt1;
            try 
            {
                preparedStmt1 = conn.prepareStatement(query1);
                ResultSet rs1 = preparedStmt1.executeQuery();

                while(rs1.next())
                {  
                    countryCodeList.add(new Location(Integer.parseInt(rs1.getString("phonecode")), rs1.getString("name") + "-" + rs1.getString("phonecode")));
                    //System.out.println(rs.getString("name"));
                    
                }
                cc.setItems(countryCodeList);
                cc.setConverter(new StringConverter<Location>() {

                    @Override
                    public String toString(Location object) {
                        return object.getName();
                    }

                    @Override
                    public Location fromString(String string) {
                        return cc.getItems().stream().filter(ap -> 
                            ap.getName().equals(string)).findFirst().orElse(null);
                    }
                });
                preparedStmt1.close();
                rs1.close();
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
                    countryCodeList.forEach((code) -> {
                        String name[] = code.getName().split("-");
                        if (newLocation.getName().toLowerCase().equals(name[0].toLowerCase()))
                        {
                            cc.setValue(code);
                        }
                    });

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
                    boolean flag = false;
                    TextField contact_no = (TextField) arg0.getSource();
                    int length = contact_no.getText().length();
                    
                    if (length > 0) {
                        String[] contacts = contact_no.getText().split(",");
                        for(int i = 0; i < contacts.length; i++) {
                            if(!contacts[i].matches("[0-9]+")) {
                                flag = true;
                            }
                        }
                    }
                    if (flag) {
                        ctNoAlert.setVisible(true);
                    }
                    else {
                        ctNoAlert.setVisible(false);
                    }
                }
        });
        
        eId.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>(){
		@Override
		public void handle(KeyEvent arg0) {
                    boolean flag = false;
                    TextField email = (TextField) arg0.getSource();
                    int length = email.getText().length();
                    Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
                    
                    if (length > 0) {
                        String[] emails = email.getText().split(",");
                        for(int i = 0; i < emails.length; i++) {
                            Matcher matcher = pattern.matcher(emails[i]);
                            if(length > 0 && !matcher.matches()) {
                                flag = true;
                            }
                        }
                    }
                    if (flag) {
                        eIdAlert.setVisible(true);
                    }
                    else {
                        eIdAlert.setVisible(false);
                    }
                }
        });

        addPhone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Add textfields
                phoneFields++;
                ComboBox<Location> comboBox1 = new ComboBox<Location>(countryCodeList);
                comboBox1.setId("cc"+phoneFields);
                country_code.add(comboBox1);
                AccordionGridPane.add(comboBox1, 1, (phoneFields + 1));
                ComboBox<Location> temp = country_code.get(index);
                temp.setConverter(new StringConverter<Location>() {

                    @Override
                    public String toString(Location object) {
                        return object.getName();
                    }

                    @Override
                    public Location fromString(String string) {
                        return temp.getItems().stream().filter(ap -> 
                            ap.getName().equals(string)).findFirst().orElse(null);
                    }
                });
                temp.setValue(cc.getValue());
                
                TextField areaCode = new TextField ();
                areaCode.setId("ctAreaCode"+phoneFields);
                ct_areaCode.add(areaCode);
                AccordionGridPane.add(areaCode, 2, (phoneFields + 1));

                
                TextField notification = new TextField ();
                notification.setId("ctNo"+phoneFields);
                ct_No.add(notification);
                AccordionGridPane.add(notification, 3, (phoneFields + 1));

                ComboBox<String> comboBox = new ComboBox<String>(phoneTypeList);
                comboBox.setId("phoneType"+phoneFields);
                phone_Type.add(comboBox);
                index++;

                AccordionGridPane.add(comboBox, 4, (phoneFields + 1));
                AccordionGridPane.setVgap(4);
                anchorPane.setMinHeight(anchorPane.getHeight() + 30);
                anchorPane.setMaxHeight(anchorPane.getHeight() + 30);
                titledPane.setMinHeight(titledPane.getHeight() + 30);
                titledPane.setMaxHeight(titledPane.getHeight() + 30);
            }
        });
            
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
        boolean flag = true;
        String phone = null;
        int length = ctNo.getText().length();
        if (length > 0 && !ctNo.getText().matches("[0-9]+")) {
            flag = false;
        }
        phone = cc.getValue().getId() + "-" + ctAreaCode.getText()+ "-" + ctNo.getText() + "(" + phoneType.getValue().charAt(0) + ")";
        
        for (int i = 0; i < index; i++) {
            TextField object = ct_No.get(i);
            TextField object1 = ct_areaCode.get(i);
            length = object.getText().length();

            if(length > 0) {
                if (!object.getText().matches("[0-9]+")) {
                    flag = false;
                }

                ComboBox combo = phone_Type.get(i);
                ComboBox<Location> combo1 = country_code.get(i);
                String value = (String) combo.getValue();
                phone = phone + "," + combo1.getValue().getId() + "-" + object1.getText() + "-" + object.getText() + "(" + value.charAt(0) + ")";
            }
        }
        
        SalesContactDetails record = new SalesContactDetails(count,ctPersonName.getText(), eId.getText(), phone);
        count++;
        
        length = eId.getText().length();

        Pattern pattern = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");

        if (length > 0) {
            String[] emails = eId.getText().split(",");
            for(int i = 0; i < emails.length; i++) {
                Matcher matcher = pattern.matcher(emails[i]);
                if(length > 0 && !matcher.matches()) {
                    flag = false;
                }
            }
        }
        if (flag) {
            SalesContactDetailsTable.getItems().add(record);
            ctPersonName.clear();
            eId.clear();
            ctNo.clear();
        }
    }
   
}
