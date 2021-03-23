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
public class Food extends Item {
    int servingSize; //pax

    public Food(int servingSize, String itemId, String itemName, double price, char category) {
        super(itemId, itemName, price, category);
        this.servingSize = servingSize;
    }
  
    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }
    
}
