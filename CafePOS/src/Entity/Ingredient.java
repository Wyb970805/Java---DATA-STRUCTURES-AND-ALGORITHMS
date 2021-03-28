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
    private String stockId;
    private String stockName;
    private int stockQuantity;
    private int lowestQuantity;
    
    public Ingredient(String stockId, String stockName, int stockQuantity, int lowestQuantity){
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockQuantity = stockQuantity;
        this.lowestQuantity = lowestQuantity;
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
    
    @Override
    public String toString() {
        return "Ingredient ID: " + stockId + "\nIngredient Name: " + stockName + "\nIngredient Quantity: " + stockQuantity + "\nLowest Quantity: " + lowestQuantity + "\n";
    }
   
}
