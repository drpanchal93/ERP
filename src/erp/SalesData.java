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
    private SimpleStringProperty prodDesc;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty unit;
    private SimpleStringProperty basicAmount;
    private SimpleStringProperty taxAmount;
    private SimpleStringProperty totalAmount;
    
    public SalesData(int srNo,String prodDescription,int qty, String unt, String basicVal, String taxVal, String totalVal)
    {
        this.serialNumber = new SimpleIntegerProperty(srNo);
        this.prodDesc = new SimpleStringProperty(prodDescription);
        this.quantity = new SimpleIntegerProperty(qty);
        this.unit = new SimpleStringProperty(unt);
        this.basicAmount = new SimpleStringProperty(basicVal);
        this.taxAmount = new SimpleStringProperty(taxVal);
        this.totalAmount = new SimpleStringProperty(totalVal);
    }

    public int getSerialNumber() {
        return serialNumber.get();
    }

    public void setSerialNumber(Integer srNo) {
        serialNumber.set(srNo);
    }

    public String getProdDesc() {
        return prodDesc.get();
    }

    public void setProdDesc(String prodDescription) {
        prodDesc.set(prodDescription);
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

    public String getBasicValue() {
        return basicAmount.get();
    }

    public void setBasicValue(String basicVal) {
        basicAmount.set(basicVal);
    }

    public String getTaxAmount() {
        return taxAmount.get();
    }

    public void setTaxAmount(String taxVal) {
        taxAmount.set(taxVal);
    }

    public String getTotalAmount() {
        return totalAmount.get();
    }

    public void setTotalAmount(String totalVal) {
        totalAmount.set(totalVal);
    }
}
