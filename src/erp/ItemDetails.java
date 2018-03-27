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
public class ItemDetails 
{
    private SimpleIntegerProperty serialNumber;
    private SimpleStringProperty itemDescription;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty rate;
    private SimpleStringProperty unit;
    private SimpleDoubleProperty amtBeforeDisc;
    private SimpleDoubleProperty discPercent;
    private SimpleDoubleProperty discAmount;
    private SimpleDoubleProperty total;
    
    public ItemDetails(Integer serialNumber, String itemDescription, Integer quantity, Double rate, String unit, Double amtBeforeDisc, Double discPercent, Double discAmount, Double total)
    {
        this.serialNumber = new SimpleIntegerProperty(serialNumber);
        this.itemDescription = new SimpleStringProperty(itemDescription);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.rate = new SimpleDoubleProperty(rate);
        this.unit = new SimpleStringProperty(unit);
        this.amtBeforeDisc = new SimpleDoubleProperty(amtBeforeDisc);
        this.discPercent = new SimpleDoubleProperty(discPercent);
        this.discAmount = new SimpleDoubleProperty(discAmount);
        this.total = new SimpleDoubleProperty(total);
    }
    
    //Set and get serial number
    
    public int getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(Integer srNo) {
        serialNumber.set(srNo);
    }
    //Set and get item description
    
    public String getItemDescription() {
        return itemDescription.get();
    }

    public void setItemDescription(String prodDesc) {
        itemDescription.set(prodDesc);
    }
    //set and get quantity
    
    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer qty) {
        quantity.set(qty);
    }
    // set and get rate
    
    public double getRate() {
        return rate.get();
    }

    public void setRate(Double rt) {
        rate.set(rt);
    }
    
    //set and get unit
    
    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String unt) {
        unit.set(unt);
    }
    
    //set and get disc percent
    public double getDiscPercent() {
        return discPercent.get();
    }

    public void setDiscPercent(Double discPerc) {
        discPercent.set(discPerc);
    }
    
    //set and get disc amount
    public double getDiscAmount() {
        return discAmount.get();
    }

    public void setDiscAmount(Double discAmt) {
        discAmount.set(discAmt);
    }
    
    //set and get amt before disc
    
    public double getAmtBeforeDisc() {
        return amtBeforeDisc.get();
    }

    public void setAmtBeforeDisc(Double amtBfrDisc) {
        amtBeforeDisc.set(amtBfrDisc);
    }
    
    //set and get total amount
    public double getTotal() {
        return total.get();
    }

    public void setTotal(Double amt) {
        total.set(amt);
    }
}
