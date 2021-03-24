//backup
package Entity;

import Entity.Order;
import java.io.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Tong Chein Leng
 */
public class Payment implements Serializable {

    //properties
    private int paymentID;
    private float paymentAmount;
    private String paymentMethod;
    private LocalDateTime paid = LocalDateTime.now();   
    private Order orderList[];
    private float totalIncome;

    //contructor
    public Payment() {
    }

    public Payment(int paymentID, float paymentAmount, String paymentMethod, LocalDateTime paid, Order orderList[], float totalIncome) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
        this.orderList = orderList;
        this.totalIncome = totalIncome;
    }

    public Payment(int paymentID, float paymentAmount, String paymentMethod, LocalDateTime paid) {
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
    }

    //setter & getter methods
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int id) {
        this.paymentID = id;
    }

    public void setPaymentAmount(float amt) {
        this.paymentAmount = amt;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getPaid() {
        return paid;
    }

    public Order[] getOrderList() {

        for (int i = 0; i <= orderList.length; i++) {
            System.out.println("Order List: " + this.orderList[i]);
        }
        return orderList;
    }

    public void setTotalIncome(float income) {
        this.totalIncome = income;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    //other methods
    public String toString() {

        DateTimeFormatter formatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = paid.format(formatObj);

        return "Payment ID        : " + paymentID
                + "\nPayment Amount    : RM " + String.format("%.2f", paymentAmount)
                + "\nPaid Date and Time: " + formattedDate
                + "\nPayment Method    : " + paymentMethod
                + "\n\n";
    }

}
