/*

 */
package Entity;

import java.util.regex.Pattern;

/**
 *
 * @author Wen
 */
public class Order {
    private int orderNum;        // 1001
    private ItemOrder itemList[];   //
    private char orderType;         // T - take away, D - Dine in
    private int tableNo;            // 01-15
    private float totalPrice;       // 0.0
    //private Member member;          // mid
    //private Staff staffIncharge;    // sid
    
    public Order(int orderNum, char orderType, int tableNo/*, Member mid,Staff sid*/){
        this.orderNum = orderNum;
        ItemOrder itemList = new ItemOrder();
        this.orderType = orderType;
        this.tableNo = tableNo;
        this.totalPrice = (float) 0.0;
        //this.member = member;
        //this.staffIncharge = staffIncharge;
    }
    
    public boolean createOrder(){
        this.orderNum = orderNum;
        ItemOrder itemList = new ItemOrder();
        this.orderType = orderType;
        this.tableNo = tableNo;
        this.totalPrice = (float) 0.0;
        //this.member = member;
        //this.staffIncharge = staffIncharge;
        return true;
    }
        
    public Order getOrder(){
        return this;
    }
    
    //public String getmemberId(){
        
    //}
    
    public int getTbNum(){
        return this.tableNo;
    }
    
    public void setTableNum(int tbNum){
        this.tableNo = tbNum;
    }
    
    public char getOrderType(){
        return this.orderType;
    }
    
    public void setOrderType(char orderType){
        this.orderType = orderType;
    }
    
    public String toString(){
        return "Order Number: " + orderNum + "\nItem Order: " + itemList 
                + "\nOrder Type: " + orderType +"\nTable No.: " + tableNo + "\nTotal: " + totalPrice 
                /*+ "Membership: " + member + "Staff Incharge: " + staffIncharge*/;
    }
        
}
