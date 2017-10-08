/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author admin
 */
public class PurchaseData 
{
    private SimpleIntegerProperty serialNumber;
    private SimpleStringProperty prodDescription;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty basicAmount;
    private SimpleIntegerProperty taxAmount;
    private SimpleIntegerProperty totalAmount;

    public PurchaseData(Integer serialNumber,String prodDescription,Integer quantity,String unit,Integer basicAmount,Integer taxAmount,Integer totalAmount)
    {
        this.serialNumber = new SimpleIntegerProperty(serialNumber);
        this.prodDescription = new SimpleStringProperty(prodDescription);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.unit = new SimpleStringProperty(unit);
        this.basicAmount = new SimpleIntegerProperty(basicAmount);
        this.taxAmount = new SimpleIntegerProperty(taxAmount);
        this.totalAmount = new SimpleIntegerProperty(totalAmount);
    }

    public int getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(Integer srNumber) {
        serialNumber.set(srNumber);
    }

    public String getProdDescription() {
        return prodDescription.get();
    }

    public void setProdDescription(String prodDesc) {
        prodDescription.set(prodDesc);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer qty) {
        quantity.set(qty);
    }

    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String unt) {
        unit.set(unt);
    }

    public Integer getBasicAmount() {
        return basicAmount.get();
    }

    public void setBasicAmount(Integer basicAmt) {
        basicAmount.set(basicAmt);
    }

    public Integer getTaxAmount() {
        return taxAmount.get();
    }

    public void setTaxAmount(Integer taxAmt) {
        taxAmount.set(taxAmt);
    }

    public Integer getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(Integer totalAmt) {
        totalAmount.set(totalAmt);
    }
    
}
