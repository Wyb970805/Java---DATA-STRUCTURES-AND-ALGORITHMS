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
public class Item {
    String itemId;
    String itemName;
    double price;
    char category;
    
    public Item(String itemId, String itemName, double price, char category){
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "itemId=" + itemId + ", itemName=" + itemName + ", price=" + price + ", category=" + category + "\n";
    }
    
    
    
}



