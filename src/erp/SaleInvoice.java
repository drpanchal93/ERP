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
public class SaleInvoice 
{
    private SimpleIntegerProperty serialNumber;
    private SimpleStringProperty  productDescription;
    private SimpleIntegerProperty hsnCode;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty Rate;
    private SimpleStringProperty Per;
    private SimpleIntegerProperty TotalAmount;
    
   public SaleInvoice(Integer serialNumber,String prodDescription,Integer quantity,Integer hsnCode,Integer Rate,String Per,Integer TotalAmount)
    {
        this.serialNumber = new SimpleIntegerProperty(serialNumber);
        this.productDescription = new SimpleStringProperty(prodDescription);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.hsnCode = new SimpleIntegerProperty(hsnCode);
        this.Rate = new SimpleIntegerProperty(Rate);
        this.Per = new SimpleStringProperty(Per);
        this.TotalAmount = new SimpleIntegerProperty(TotalAmount);
    } 
   
    public Integer getSerialNumber() {
        return serialNumber.get();
    }
    
    public void setSerialNumber(Integer serialNo) {
        serialNumber.set(serialNo);
    }

    public String getProductDescription() {
        return productDescription.get();
    }
    
    public void setProductDescription(String prodDescription) {
        productDescription.set(prodDescription);
    }

    public Integer getHsnCode() {
        return hsnCode.get();
    }
    
    public void setHsnCode(Integer HSNCode) {
        hsnCode.set(HSNCode);
    }

    public Integer getQuantity() {
        return quantity.get();
    }
    
    public void setQuantity(Integer quant) {
        quantity.set(quant);
    }

    public Integer getRate() {
        return Rate.get();
    }
    
    public void setRate(Integer rate) {
        Rate.set(rate);
    }

    public String getPer() {
        return Per.get();
    }
    
    public void setPer(String per) {
        Per.set(per);
    }

    public Integer getTotalAmount() {
        return TotalAmount.get();
    }
    
    public void setTotalAmount(Integer totalAmount) {
        TotalAmount.set(totalAmount);
    }
}
