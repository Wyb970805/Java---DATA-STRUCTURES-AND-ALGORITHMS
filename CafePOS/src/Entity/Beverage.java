/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Wen
 */
public class Beverage extends Item {
    int volumn; // ml
    
    public Beverage (String itemId, String itemName, double price, char category, int volumn){
        super(itemId,itemName,price,category);
        this.volumn = volumn;
        
    }

    public int getVolumn() {
        return volumn;
    }

    public void setVolumn(int volumn) {
        this.volumn = volumn;
    }
}
