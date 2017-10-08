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
public class SalesData 
{
    private SimpleIntegerProperty serialNumber;
    private SimpleStringProperty prodDescription;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty basicAmount;
    private SimpleIntegerProperty taxAmount;
    private SimpleIntegerProperty totalAmount;
    
    public SalesData(Integer serialNumber,String prodDescription,Integer quantity, String unt, Integer basicAmount, Integer taxAmount, Integer totalAmount)
    {
        this.serialNumber = new SimpleIntegerProperty(serialNumber);
        this.prodDescription = new SimpleStringProperty(prodDescription);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.unit = new SimpleStringProperty(unt);
        this.basicAmount = new SimpleIntegerProperty(basicAmount);
        this.taxAmount = new SimpleIntegerProperty(taxAmount);
        this.totalAmount = new SimpleIntegerProperty(totalAmount);
    }

    public int getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(Integer srNo) {
        serialNumber.set(srNo);
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

    public int getBasicAmount() {
        return basicAmount.get();
    }

    public void setBasicAmount(Integer basicVal) {
        basicAmount.set(basicVal);
    }

    public int getTaxAmount() {
        return taxAmount.get();
    }

    public void setTaxAmount(Integer taxVal) {
        taxAmount.set(taxVal);
    }

    public int getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(Integer totalVal) {
        totalAmount.set(totalVal);
    }
}
