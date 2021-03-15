package Entity;

/**
 *
 * @author Tong Chein Leng
 */
public class CreditCard {

    private int cardNo;
    private String nameOnCard;
    private String expiryDate;
    private float totalCCAmount;

    public CreditCard() {
    }

    public CreditCard(int cardNo, String nameOnCard, String expiryDate, float totalCCAmount) {
        this.cardNo = cardNo;
        this.nameOnCard = nameOnCard;
        this.expiryDate = expiryDate;
        this.totalCCAmount = totalCCAmount;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
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

    public void setCCTotalAmt(float ccAmt) {
        this.totalCCAmount = ccAmt;
    }
    
    public static boolean validateName(String name){	
    	for(int i=0; i<name.length(); i++){
    		if(!Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))){
    			return false;
    		}
    	}
    	return true;
    }
    

    public String toString() {
        return "Card No: " + cardNo
                + "Card Holder: " + nameOnCard
                + "Card Expiry Date: " + expiryDate;
    }
}