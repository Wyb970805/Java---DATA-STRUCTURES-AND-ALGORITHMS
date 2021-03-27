/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import Entity.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import static cafepos.RecordPayment.*;

/**
 *
 * @author Tong Chein Leng
 */
public class EditPayment {

    Cash blc = new Cash();
    CreditCard cc = new CreditCard();
    LocalDateTime paid = LocalDateTime.now();

    public static void modifyPayment() {
        Scanner scanMethod = new Scanner(System.in);
        int method = 0;
        boolean selectIsDigit = false;
        EditPayment ep = new EditPayment();
        do {
            do {
                try {
                    System.out.println("\t\t\t\t          Edit Payment Methods ");
                    System.out.println("\t\t\t\t _________________________________");
                    System.out.println("\t\t\t\t| No. |          Actions          |");
                    System.out.println("\t\t\t\t|  1  | Cash Records              |");
                    System.out.println("\t\t\t\t|  2  | Credit Card Records       |");
                    System.out.println("\t\t\t\t|_____|___________________________|\n");
                    System.out.print("Please enter a number : ");
                    method = scanMethod.nextInt();
                    selectIsDigit = true;
                } catch (Exception e) {
                    System.out.println("Please enter an integer number and try again!");
                    scanMethod.nextLine();
                }
            } while (selectIsDigit == false);
            switch (method) {
                case 1:
                    if (cashIterator.isEmpty() == true) {
                        System.out.println("Cash Records List is empty!");
                        yesNo();
                    } else {
                        ep.searchCash();
                    }
                    break;
                case 2:
                    if (creditCardIterator.isEmpty() == true) {
                        System.out.println("Credit Card Records List is empty!");
                        yesNo();
                    } else {
                        ep.searchCreditCard();
                    }
                    break;
                default:
                    errorMessage();
            }
        } while (method < 1 || method > 2);
    }

    public void searchCash() {
       Scanner scanRecords = new Scanner(System.in);
        boolean xid = true;
        do {
            System.out.printf("Enter Payment ID: ");
            int id = scanRecords.nextInt();
            int index = id - 1;
            if (cashIterator.contains(index) == true) {
                System.out.println(cashIterator.getEntry(id));
                char yn;
                System.out.printf("Do you want to modify this records? (y = yes): ");
                yn = scanRecords.next().charAt(0);
                if (yn == 'y' || yn == 'Y') {
                    mCash(id);
                } else {
                    yesNo();
                }
                xid = false;
            } else {
                System.out.println("Payment ID do not exist!");
            }
        } while (xid == true);
    }

    public void searchCreditCard() {
        Scanner scanRecords = new Scanner(System.in);
        boolean xid = true;
        do {
            System.out.printf("Enter Payment ID: ");
            int id = scanRecords.nextInt();
            int index = id - 1;
            if (creditCardIterator.contains(index) == true) {
                System.out.println(creditCardIterator.getEntry(id));
                char yn;
                System.out.printf("Do you want to modify this records? (y = yes): ");
                yn = scanRecords.next().charAt(0);
                if (yn == 'y' || yn == 'Y') {
                    mCard(id);
                } else {
                    yesNo();
                }
                xid = false;
            } else {
                System.out.println("Payment ID do not exist!");
            }
        } while (xid == true);
    }

    public void mCash(int id) {
        Scanner scanCash = new Scanner(System.in);
        float amt = 0;
        boolean is2dec = false;
        float change = 0;
        float cashBlc = 0;
        String paymentMethod = "Cash";
        float paymentAmount = cashIterator.getEntry(id).getPaymentAmount();
        Order order = cashIterator.getEntry(id).getOrder();
        do {
            try {
                System.out.printf("Enter paid amount: RM ");
                amt = scanCash.nextFloat();

                if (amt > paymentAmount) {
                    change = amt - paymentAmount;
                    cashBlc = blc.getBalance() - change + amt;
                    blc.setBalance(cashBlc);
                    is2dec = true;
                } else if (paymentAmount == amt) {
                    cashBlc = blc.getBalance() + amt;
                    blc.setBalance(cashBlc);
                    is2dec = true;
                } else {
                    System.out.println("Please pay for the order!!");
                }
            } catch (Exception e) {
                System.out.println("Please enter an integer number and try again!");
                scanCash.nextLine();
            }
        } while (is2dec == false);
        Cash cash1 = new Cash(id, order, paymentAmount, paymentMethod, paid, change, amt);
        boolean editCashInfo = cashIterator.replace(id, cash1);
        if (editCashInfo == true) {
            System.out.println("\nReceipt\n---------");
            System.out.print(cashIterator.getEntry(id));
            System.out.println("Modify successfully.");
        } else {
            System.out.println("An error occurred.");
        }
        yesNo();
    }

    public void mCard(int id) {
        Scanner scanCard = new Scanner(System.in);
        Scanner scanName = new Scanner(System.in);
        Scanner scanDate = new Scanner(System.in);
        boolean isDigit = false;
        String cardNo;
        String name;
        String expiryDate;
        String paymentMethod = "Credit Card";
        boolean check = false;
        boolean check1 = false;
        float paymentAmount = creditCardIterator.getEntry(id).getPaymentAmount();
        Order order = creditCardIterator.getEntry(id).getOrder();
        do {
            System.out.printf("Credit card No.: ");
            cardNo = scanCard.nextLine();
            if (cardNo.matches("\\d{16}")) {
                isDigit = true;
            } else {
                System.out.println("Please enter 16 digits!");
            }
        } while (isDigit == false);
        do {
            System.out.printf("Name of Card Holder: ");
            name = scanName.nextLine();
            if (cc.validateName(name) == false) {
                System.out.println("Only Letters & Space! Please try again!");
            } else {
                check = true;
            }
        } while (check == false);
        do {
            System.out.printf("Expiry Date (MM/yy): ");
            expiryDate = scanDate.nextLine();

            if (expiryDate.matches("\\d{2}/\\d{2}")) {
                check1 = true;
            } else {
                System.out.println("Please enter correct format and try again!");
            }
        } while (check1 == false);
        CreditCard credit = new CreditCard(id, order, paymentAmount, paymentMethod, paid, cardNo, name, expiryDate);
        boolean editCardInfo = creditCardIterator.replace(id, credit);
        if (editCardInfo == true) {
            System.out.println("\nReceipt\n---------");
            System.out.println(creditCardIterator.getEntry(id));
            System.out.println("Credit Card Info was modified Successfully.\n");
        } else {
            System.out.println("Credit Card Info was Failed to modify in List");
        }
        yesNo();
    }
}
