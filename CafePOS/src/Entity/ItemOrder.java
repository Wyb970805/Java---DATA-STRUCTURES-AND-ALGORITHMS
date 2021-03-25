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
public class ItemOrder {
    private int itemNum = 0;
    private Item itemOrder;
    private int itemQty;
    private double subTotal;
    
    public ItemOrder(){
        
    }
    
    public ItemOrder(int itemNum, Item itemOrder, int qty){
        this.itemNum = itemNum;
        this.itemOrder = itemOrder;
        this.subTotal = calculateSubTotal(qty);
        this.itemQty = qty;
    }
    
    public ItemOrder getItemOrder(){
        return this;
    }

    public int getItemQty() {
        return itemQty;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }
    
    public double calculateSubTotal(int qty){
        return itemOrder.getPrice()*qty;
    }

    @Override
    public String toString() {
        
        return String.format("%3d. %-6s %-20s %3d %3.2f", itemNum+1,itemOrder.getItemId(), itemOrder.getItemName(), itemQty, subTotal);
    }
    
}

// for loop to return each item.