/*

 */
package Entity;

/**
 *
 * @author Wen
 */
public class Order {
    private String orderNum;
    private ItemOrder itemList[];
    private char orderType;
    private int tableNo;
    private float totalPrice;
    private Member member;
    private Staff staffIncharge;
    
    public void createOrder(){
        this.orderNum = orderNum;
        ItemOrder itemList = new ItemOrder();
        this.orderType = orderType;
        this.tableNo = tableNo;
        this.totalPrice = 0;
        this.member = member;
        this.staffIncharge = staffIncharge;
    }
    
    public Order getOrder(){
        return this;
    }
    
    public String toString(){
        return "Order Number: " + orderNum+ "Item Order: " + itemList 
                + "Table No.: " + tableNo + "Total: " + totalPrice 
                + "Membership: " + member + "Staff Incharge: " + staffIncharge;
    }
}
