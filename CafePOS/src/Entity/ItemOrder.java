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

    private int itemNum;
    private Item itemOrder;
    private int itemQty;
    private double subTotal;

    public ItemOrder() {

    }

    public ItemOrder(int itemNum, Item itemOrder, int qty) {
        //this.count++;
        this.itemNum = itemNum;
        this.itemOrder = itemOrder;
        this.itemQty = qty;
        this.subTotal = calculateSubTotal(qty);
    }
    
    public ItemOrder(int itemNum, Item itemOrder, int qty, double subTotal) {
        //this.count++;
        this.itemNum = itemNum;
        this.itemOrder = itemOrder;
        this.itemQty = qty;
        this.subTotal = subTotal;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public ItemOrder getItemOrder() {
        return this;
    }

    public void setItemOrder(Item itemOrder) {
        this.itemOrder = itemOrder;
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

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double calculateSubTotal(int qty) {
        return itemOrder.getPrice() * qty;
    }

    @Override
    public String toString() {

        return String.format("%3d. %-6s %-20s %3d %3.2f", itemNum, itemOrder.getItemId(), itemOrder.getItemName(), itemQty, subTotal);
    }

}

// for loop to return each item.
