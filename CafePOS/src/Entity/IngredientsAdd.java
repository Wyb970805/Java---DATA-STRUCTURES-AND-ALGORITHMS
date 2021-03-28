/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import ADT.ArraySet;
/**
 *
 * @author User10
 */
public class IngredientsAdd {
    private String itemId;
    private String itemName;
    private double price;
    private Ingredient Ingredients;
    private char category;
    //ArraySet<Ingredient> IngredientList;
    
    public IngredientsAdd(Ingredient Ingredients){
        
        this.Ingredients = Ingredients;
        //this.IngredientList = new ArraySet<Ingredient>();
        //this.IngredientList = new Ingredient();
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
    
    public IngredientsAdd getIngredients() {
        return this;
    }

    public void setIngredients(Ingredient Ingredients) {
        this.Ingredients = Ingredients;
    }

    public char getCategory() {
        return category;
    }

    public void setCategory(char category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return ""+ Ingredients.getStockName();
    }
    
    
    
}

