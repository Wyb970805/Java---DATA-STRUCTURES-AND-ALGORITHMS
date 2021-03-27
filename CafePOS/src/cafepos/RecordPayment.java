/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import java.util.Scanner;
import Entity.*;
import ADT.*;
import static cafepos.MemberModule.ListMember;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import static cafepos.OrderModule.orderLine;
import static cafepos.StaffModule.ListStaff;

/**
 *
 * @author Tong Chein Leng
 */
public class RecordPayment {

    OrderModule orderModule = new OrderModule();
    EditPayment ep = new EditPayment();
    static IteratorInterface<Cash> cashIterator = new ListIterator<>();
    static IteratorInterface<CreditCard> creditCardIterator = new ListIterator<>();
    static Payment pay = new Payment();
    static Cash blc = new Cash();
    static CreditCard cc = new CreditCard();
    static float paymentAmount = 0;
    Order order = orderLine.getFirst();
    LocalDateTime paid = LocalDateTime.now();

    public static void records() {
        //First Record
        Member member = ListMember.getEntry(1);
        Staff staff = ListStaff.getEntry(1);
        Order order1 = new Order(1001, 'T', 1, member, staff);
        float income = 0;
        String dateTime = "2021-01-09 11:55:55";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime formatDateTime = LocalDateTime.parse(dateTime, formatter);
        Cash cashList1 = new Cash(1, order1, 100, "Cash", formatDateTime, 0, 100);
        if (cashIterator.add(cashList1) == true) {
            blc.setBalance(100);
            income = pay.getTotalIncome() + 100;
            pay.setTotalIncome(income);
        }
        //Second Record
        Order order2 = new Order(1002, 'T', 1, member, staff);
        String ccdateTime = "2021-02-16 08:05:55";
        LocalDateTime ccformatDateTime = LocalDateTime.parse(ccdateTime, formatter);
        CreditCard cardList1 = new CreditCard(1, order2, 200, "Credit Card", ccformatDateTime, "1997080520000216", "Wang Yi Bo", "02/24");
        if (creditCardIterator.add(cardList1) == true) {
            cc.setCCTotalAmt(200);
            income = pay.getTotalIncome() + 200;
            pay.setTotalIncome(income);
        }
    }

    public static void paymentSystem() {
        RecordPayment rp = new RecordPayment();
        Scanner scan = new Scanner(System.in);
        boolean selectIsDigit = false;
        int selectOrderNo = 0;
        do {
            do {
                try {
                    System.out.println("\n\t\t\t\t  Make Payment Module \n\t\t\t\t_________________");
                    System.out.printf("Please enter Order No. (0 = exit):  ");
                    selectOrderNo = scan.nextInt();
                    selectIsDigit = true;
                } catch (Exception e) {
                    System.out.println("Please enter an integer number and try again!");
                    scan.nextLine();
                }
            } while (selectIsDigit == false);

            if (selectOrderNo >= 1001 && selectOrderNo == orderLine.getFirst().getOrderNum()) {

                System.out.println("Order No: " + selectOrderNo);
                System.out.println("Customer: " + orderLine.getFirst().getMember().getName());
                paymentAmount = (float) orderLine.getFirst().getTotalPrice();
                // Not member price
                System.out.printf("Total Amount: RM %.2f \n", paymentAmount);
                
                if ("M1000".equals(orderLine.getFirst().getMember().getMember_ID())) {
                    System.out.printf("Total Amount for Guest: RM %.2f \n", paymentAmount);
                } else {
                    paymentAmount = paymentAmount * (float) 0.8;
                    System.out.printf("Total Amount for Member: RM %.2f \n", paymentAmount);
                }
                rp.selectPaymentMethod();
                break;
            } else if (selectOrderNo == 0) {
                break;
            } else {
                System.out.println("Wrong Order No.! Please enter correct Order No.!");
            }
        } while ((selectOrderNo < 1001 || selectOrderNo != orderLine.getFirst().getOrderNum()) && selectOrderNo != 0);
    }

    public void selectPaymentMethod() {
        Scanner scanMethod = new Scanner(System.in);
        int method = 0;
        boolean selectIsDigit = false;
        do {
            do {
                try {
                    System.out.println("\t\t\t\t             Payment Methods ");
                    System.out.println("\t\t\t\t _________________________________");
                    System.out.println("\t\t\t\t| No. |          Actions          |");
                    System.out.println("\t\t\t\t|  1  | Cash                      |");
                    System.out.println("\t\t\t\t|  2  | Credit Card               |");
                    System.out.println("\t\t\t\t|  0  | Select Order No. again    |");
                    System.out.println("\t\t\t\t|_____|___________________________|\n");
                    System.out.print("Please enter a number : ");
                    method = scanMethod.nextInt();
                    selectIsDigit = true;
                } catch (Exception e) {
                    System.out.println("Please enter an integer number and try again!");
                    scanMethod.nextLine();
                }
            } while (!selectIsDigit);

            switch (method) {
                case 1:
                    cash();
                    addIncome();
                    yesNo();
                    break;
                case 2:
                    card();
                    addIncome();
                    yesNo();
                    break;
                case 0:
                    break;
                default:
                    errorMessage();
            }
        } while (method < 0 || method > 2);
    }

    public void cash() {
        Scanner scanCash = new Scanner(System.in);
        float amt = 0;
        boolean is2dec = false;
        float change = 0;
        float cashBlc = 0;
        String paymentMethod = "Cash";
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

        int paymentID = 2;
        while (cashIterator.containsID(paymentID) == true) {
            paymentID++;
        }
        Cash cash1 = new Cash(paymentID, order, paymentAmount, paymentMethod, paid, change, amt);
        if (cashIterator.add(cash1) == true) {
            blc.setChange(change);
            System.out.printf("\nReceived Cash: RM %.2f\n", amt);
            System.out.printf("Change: RM %.2f \n", change);
            System.out.println("Pay by Cash successfully.");
            System.out.println("\nReceipt\n---------");
            System.out.println(cashIterator.getEntry(paymentID));
            orderLine.getFirst().setTotalPrice(paymentAmount);
            orderModule.updateOrderStatus();
        } else {
            System.out.println("ERROR!");
        }
    }

    public void card() {
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

        int paymentID = 2;
        while (creditCardIterator.containsID(paymentID) == true) {
            paymentID++;
        }
        float amt = cc.getCCTotalAmt() + paymentAmount;
        cc.setCCTotalAmt(amt);
        CreditCard credit = new CreditCard(paymentID, order, paymentAmount, paymentMethod, paid, cardNo, name, expiryDate);
        if (creditCardIterator.add(credit) == true) {
            System.out.printf("\nPay by credit card: RM %.2f\n", paymentAmount);
            System.out.println("Credit Card Info was added Successfully.\n");
            System.out.println("\nReceipt\n---------");
            System.out.println(creditCardIterator.getEntry(paymentID));
            orderLine.getFirst().setTotalPrice(paymentAmount);
            orderModule.updateOrderStatus();
        } else {
            System.out.println("Credit Card Info was Failed to add in List");
        }
    }

    public void addIncome() {
        float income = pay.getTotalIncome() + paymentAmount;
        pay.setTotalIncome(income);
        int total = creditCardIterator.getLength() + cashIterator.getLength();
        System.out.println("Total number of payment bills: " + total);
    }

    public static void yesNo() {
        System.out.println("Do you want to continue?");
    }

    public static void errorMessage() {
        System.out.println("Input out of range detected. Please enter input within the range!");
    }

    public static <T> void displayList(IteratorInterface<T> paymentList) {
        Iterator iterator = paymentList.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void displayRecords() {
        int total = creditCardIterator.getLength() + cashIterator.getLength();
        System.out.println("\nDisplay Payment Records\n------------------------");
        System.out.printf("Cash Balance: RM %.2f \n", blc.getBalance());
        System.out.printf("Total amount from credit card: RM %.2f \n", cc.getCCTotalAmt());
        System.out.println("Total number of records paid by cash: " + cashIterator.getLength());
        System.out.println("Total number of records paid by credit card: " + creditCardIterator.getLength());
        System.out.println("Total number of payment bills: " + total);
        System.out.printf("Total income: RM %.2f\n\n", pay.getTotalIncome());
        System.out.println("Cash List\n---------");
        if (cashIterator.isEmpty() == true) {
            System.out.println("The Cash List is empty.");
        } else {
            displayList(cashIterator);
        }
        System.out.println("Credit Card List\n------------------");
        if (creditCardIterator.isEmpty() == true) {
            System.out.println("The Cash List is empty.");
        } else {
            displayList(creditCardIterator);
        }
    }
}
