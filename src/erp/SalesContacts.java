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
    private SimpleStringProperty city;
    private SimpleStringProperty state;
    private SimpleStringProperty country;
    private SimpleStringProperty panNo;
    private SimpleStringProperty gstNo;

    public SalesContacts(int id, String name, String city, String state, String country, String panNo, String gstNo) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.city = new SimpleStringProperty(city);
        this.state = new SimpleStringProperty(state);
        this.country = new SimpleStringProperty(country);
        this.panNo = new SimpleStringProperty(panNo);
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

    public String getCity() {
        return city.get();
    }

    public void setCity(String ct) {
        city.set(ct);
    }

    public String getState() {
        return state.get();
    }

    public void setState(String st) {
        state.set(st);
    }

    public String getCountry() {
        return country.get();
    }

    public void setCountry(String ctry) {
        country.set(ctry);
    }

    public String getPanNo() {
        return panNo.get();
    }

    public void setPanNo(String pan_No) {
        panNo.set(pan_No);
    }

    public String getGstNo() {
        return gstNo.get();
    }

    public void setGstNo(String gst_No) {
        gstNo.set(gst_No);
    }
}
