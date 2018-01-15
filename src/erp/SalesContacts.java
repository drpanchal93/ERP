/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Chaitya
 */
public class SalesContacts {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty address;
    private SimpleStringProperty gstNo;

    public SalesContacts(int id, String name, String address, String gstNo) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.gstNo = new SimpleStringProperty(gstNo);
    }

    public Integer getId() {
        return id.get();
    }

    public void setId(Integer Id) {
        id.set(Id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String nm) {
        name.set(nm);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String add) {
        address.set(add);
    }
    
    public String getGstNo() {
        return gstNo.get();
    }

    public void setGstNo(String gst_No) {
        gstNo.set(gst_No);
    }
}
