/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import javafx.beans.property.*;

/**
 *
 * @author Chaitya
 */
public class SalesContactDetails {
    
    private SimpleStringProperty name;
    private SimpleStringProperty email;
    private SimpleStringProperty phone;

    public SalesContactDetails(String name,String email,String phone)
    {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String Nm) {
        name.set(Nm);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String Em) {
        email.set(Em);
    }

    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String Ph) {
        phone.set(Ph);
    }
    
}
