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
public class CreditCard extends Payment{
    //properties
    private String cardNo;
    private String nameOnCard;
    private String expiryDate;
    private float totalCCAmount;
    
    //contructor
    public CreditCard() {
    }

    public CreditCard(int paymentID, float paymentAmount, String paymentMethod, LocalDateTime paid, String cardNo, String nameOnCard, String expiryDate, float totalCCAmount) {
        super(paymentID, paymentAmount, paymentMethod, paid);
        this.cardNo = cardNo;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.totalCCAmount = totalCCAmount;
    }
    
    public CreditCard(int paymentID, float paymentAmount, String paymentMethod, LocalDateTime paid, String cardNo, String nameOnCard, String expiryDate) {
        super(paymentID, paymentAmount, paymentMethod, paid);
        this.cardNo = cardNo;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
    }
    
    //setter & getter methods
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public float getCCTotalAmt() {
        return totalCCAmount;
    }

    public void setCCTotalAmt(float paymentAmt) {
        this.totalCCAmount = paymentAmt;
    }
    
    //other methods    
    public static boolean validateName(String name){	
    	for(int i=0; i<name.length(); i++){
    		if(!Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))){
    			return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public String toString() {
        return super.toString()
                + "Card No         : " + cardNo
                + "\nCard Holder     : " + nameOnCard
                + "\nCard Expiry Date: " + expiryDate
                + "\n";
    }
}
