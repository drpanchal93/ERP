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
public class Location {
    
    private int id;
    private String name;
    
    public Location(int id, String name) {
        
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
}
