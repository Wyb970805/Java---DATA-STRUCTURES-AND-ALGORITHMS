/*

 */
package Entity;

import ADT.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Wen
 */
public class Order {

    private int orderNum;        // 1001
    ArrayList<ItemOrder> OrderList;
    private char orderType;         // T - take away, D - Dine in
    private int tableNo;            // 01-15
    private double totalPrice;       // 0.0
    private Member member;          // mid
    private Staff staffIncharge;    // sid

    public Order(int orderNum, /*ArrayList<ItemOrder> orderList,*/ char orderType, int tableNo,/*, double totalPrice*/ Member member,Staff staffIncharge) {
        this.orderNum = orderNum;
        this.OrderList = new ArrayList<ItemOrder>();
        this.orderType = orderType;
        this.tableNo = tableNo;
        this.totalPrice = 0.0;
        this.member = member;
        this.staffIncharge = staffIncharge;
    }

    public Order getOrder() {
        return this;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public ArrayList<ItemOrder> getOrderList() {
        return OrderList;
    }

    public void setOrderList(ArrayList<ItemOrder> OrderList) {
        this.OrderList = OrderList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTableNo() {
        return this.tableNo;
    }

    public char getOrderType() {
        return this.orderType;
    }

    public void setOrderType(char orderType) {
        this.orderType = orderType;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Staff getStaffIncharge() {
        return staffIncharge;
    }

    public void setStaffIncharge(Staff staffIncharge) {
        this.staffIncharge = staffIncharge;
    }

    public String toString() {
        return "\nOrder Number: " + orderNum
                + "\nItem Order: \n"
                + String.format("%-3s %-6s %-20s %3s %8s\n", "No.", "Item(s)", "Name", "Qty", "Price(RM)")
                + OrderList
                + "\nOrder Type: " + orderType
                + "\nTable No.: " + tableNo
                + "\nTotal: RM" + totalPrice
                + "\nMembership: " + member.getMember_ID()
                + "\nStaff Incharge: " + staffIncharge.getStaff_ID() + "\n";
    }

}
