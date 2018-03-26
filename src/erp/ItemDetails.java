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
    private SimpleDoubleProperty amount;
    
    public ItemDetails(Integer serialNumber, String itemDescription, Integer quantity, Double rate, String unit, Double amtBeforeDisc, Double discPercent, Double discAmount, Double amount)
    {
        this.serialNumber = new SimpleIntegerProperty(serialNumber);
        this.itemDescription = new SimpleStringProperty(itemDescription);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.rate = new SimpleDoubleProperty(rate);
        this.unit = new SimpleStringProperty(unit);
        this.amtBeforeDisc = new SimpleDoubleProperty(amtBeforeDisc);
        this.discPercent = new SimpleDoubleProperty(discPercent);
        this.discAmount = new SimpleDoubleProperty(discAmount);
        this.amount = new SimpleDoubleProperty(amount);
    }

    public int getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(Integer srNo) {
        serialNumber.set(srNo);
    }

    public String getItemDescription() {
        return itemDescription.get();
    }

    public void setItemDescription(String prodDesc) {
        itemDescription.set(prodDesc);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer qty) {
        quantity.set(qty);
    }
    
    public double getRate() {
        return rate.get();
    }

    public void setQuantity(Double rt) {
        rate.set(rt);
    }

    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String unt) {
        unit.set(unt);
    }

    public double getDiscPercent() {
        return discPercent.get();
    }

    public void setDiscPercent(Double discPerc) {
        discPercent.set(discPerc);
    }

    public double getDiscAmount() {
        return discAmount.get();
    }

    public void setTaxAmount(Double discAmt) {
        discAmount.set(discAmt);
    }

    public double getAmtBeforeDisc() {
        return amtBeforeDisc.get();
    }

    public void setAmtBeforeDisc(Double amtBfrDisc) {
        amtBeforeDisc.set(amtBfrDisc);
    }

    public double getAmount() {
        return amount.get();
    }

    public void setTotalAmount(Double amt) {
        amount.set(amt);
    }
}
