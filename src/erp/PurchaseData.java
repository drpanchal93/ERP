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

    public PurchaseData(int srNumber,String prodDesc,int qty,String unt,int basicAmt,int taxAmt,int totalAmt)
    {
        this.serialNumber = new SimpleIntegerProperty(srNumber);
        System.out.print(serialNumber);
        this.prodDescription = new SimpleStringProperty(prodDesc);
        this.quantity = new SimpleIntegerProperty(qty);
        this.unit = new SimpleStringProperty(unt);
        this.basicAmount = new SimpleIntegerProperty(basicAmt);
        this.taxAmount = new SimpleIntegerProperty(taxAmt);
        this.totalAmount = new SimpleIntegerProperty(totalAmt);
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
