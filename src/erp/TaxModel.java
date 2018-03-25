/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

/**
 *
 * @author Chaitya
 */
public class TaxModel {
    
    private int id;
    private String name;
    private double taxRate;
    
    public TaxModel(int id, String name, double taxRate) {
        
        this.id = id;
        this.name = name;
        this.taxRate = taxRate;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public double getTaxRate()
    {
        return this.taxRate;
    }
}
