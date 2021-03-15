package Entity;

/**
 *
 * @author Tong Chein Leng
 */
public class Cash {

    private float balance;
    private float change;

    public Cash() {
    }

    public Cash(float balance, float change) {
        this.balance = balance;
        this.change = change;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float blc) {
        this.balance = blc;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }

    public void addBalance(float paid) {
        this.balance += paid;
    }

    public void subtBalance(float paid) {
        this.balance -= paid;
    }

    public String toString() {
        return "Balance: RM " + balance + "Change: RM " + change + "\n";
    }
}
