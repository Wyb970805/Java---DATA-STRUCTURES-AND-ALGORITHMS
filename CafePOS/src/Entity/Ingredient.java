/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author User10
 */
public class Ingredient {
    String stockId;
    String stockName;
    int stockQuantity;
    int lowestQuantity;
    int lowestAllowed;
    
    public Ingredient(String stockId, String stockName, int stockQuantity, int lowestQuantity, int lowestAllowed){
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockQuantity = stockQuantity;
        this.lowestQuantity = lowestQuantity;
        this.lowestAllowed = lowestAllowed;
    }
    
    public Ingredient getIngredient(){
        return this;
    }
    
    public Ingredient setIngredient(){
        return this;
    }
    
    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
    
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public int getLowestQuantity() {
        return lowestQuantity;
    }

    public void setLowestQuantity(int lowestQuantity) {
        this.lowestQuantity = lowestQuantity;
    }
    
    public int getLowestAllowed() {
        return lowestAllowed;
    }

    public void setLowestAllowed(int lowestAllowed) {
        this.lowestAllowed = lowestAllowed;
    }
    
    public String toString() {
        return "stockId: " + stockId + "\nstockName: " + stockName + "\nstockQuantity: " + stockQuantity + "\nlowestQuantity: " + lowestQuantity + "\nlowestAllowed: " + lowestAllowed +"\n";
    }
   
}

