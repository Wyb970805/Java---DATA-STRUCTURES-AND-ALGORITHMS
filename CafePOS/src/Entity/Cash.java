/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDateTime;

/**
 *
 * @author Tong Chein Leng
 */
public class Cash extends Payment{
    
    //properties
    private float balance; // the cash balance that is stored in POS System
    private float change;
    private float custPaidAmt; // cash amount that customer paid 
    
    //constructor
    public Cash() {
    }

    public Cash(float balance, float change, float custPaidAmt) {
        this.balance = balance;
        this.change = change;
        this.custPaidAmt = custPaidAmt;
    }
    
    public Cash(int paymentID, float paymentAmount, String paymentMethod, LocalDateTime paid, float change, float custPaidAmt) {
        super(paymentID, paymentAmount, paymentMethod, paid);
        this.change = change;
        this.custPaidAmt = custPaidAmt;
    }
    
    //setter & getter methods
    public float getBalance() {
        return balance;
    }

    public void setBalance(float amt) {
        this.balance = amt;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }
    
    public float getCustPaidAmt (){
        return custPaidAmt;
    }
    
    public void setCustPaidAmt(float custPaidAmt){
        this.custPaidAmt = custPaidAmt;
    }
    
    //other methods
    @Override
    public String toString() {        
        return   super.toString()
                + "Paid Amount by customer: RM " + String.format("%.2f", custPaidAmt)
                + "\nChange                 : RM " + String.format("%.2f", change) 
                + "\n\n";
    }
}
