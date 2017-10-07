/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import javafx.beans.property.*;

/**
 *
 * @author admin
 */
public class SJO {
    
    private SimpleIntegerProperty serialNumber;
    private SimpleStringProperty itemDescript;
    private SimpleIntegerProperty quant;
    
    
    public SJO(int srNumber,String itemDesc,int qty)
    {
        this.serialNumber = new SimpleIntegerProperty(srNumber);
        this.itemDescript = new SimpleStringProperty(itemDesc);
        this.quant = new SimpleIntegerProperty(qty);
    }

    public int getSrNo() {
        return serialNumber.get();
    }

    public void setSrNo(Integer srNumber) {
        serialNumber.set(srNumber);
    }

    public String getItemDescription() {
        return itemDescript.get();
    }
    public void setItemDescription(String itemDesc) {
        itemDescript.set(itemDesc);
    }

    public int getQuantity() {
        return quant.get();
    }

    public void setQuantity(Integer qty) {
        quant.set(qty);
    }
    
    
    
}
