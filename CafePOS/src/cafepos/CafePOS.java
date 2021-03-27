/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import Entity.Item;
import Entity.Member;
import Entity.Staff;
import java.util.Scanner;
import static cafepos.EditPayment.modifyPayment;
import static cafepos.ItemModule.ItemSet;
import static cafepos.MemberModule.ListMember;
import static cafepos.MemberModule.menuMember;
import static cafepos.OrderModule.orderLine;
import static cafepos.RecordPayment.*;
import static cafepos.StaffModule.ListStaff;
import static cafepos.StaffModule.menuStaff;

/**
 *
 * @author Wen
 */
public class CafePOS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        checkLogIn();

    }

    public static void userRecords() {
        ListStaff.add(new Staff("Jane", "012-4896247", "jane@gmail.com", "123456", "Barista", 1000, 1500));
        ListStaff.add(new Staff("Jason", "017-1534798", "jason@gmail.com", "456789", "Chief", 1000, 1500));
        ListStaff.add(new Staff("Alex", "019-4851365", "alex@gmail.com", "987654", "Waiter", 1000, 1500));
        ListMember.add(new Member("Adam", "016-5486248", "adam@gmail.com"));
        ListMember.add(new Member("David", "018-1364897", "david@gmail.com"));
        ListMember.add(new Member("Davinson", "014-1236484", "davinson@gmail.com"));
        ItemSet.add(new Item("F1001", "food1", 5.0, 'F'));
        ItemSet.add(new Item("F1002", "food2", 7.5, 'F'));
        ItemSet.add(new Item("B1001", "drink1", 5.0, 'B'));
        ItemSet.add(new Item("B1002", "drink2", 3.5, 'B'));
    }

    public static void checkLogIn() {
        int session = 0;

        ListStaff.add(new Staff("owner", "ownerPhone", "ownerEmail@gmail.com", "123456", "Owner", 1500, 10000));
        ListMember.add(new Member("guestName", "guestPhoneNo", "guestEmail@gmail.com"));

        Scanner scanlog = new Scanner(System.in);

        do {
            System.out.print("Enter Log In User ID : ");
            String ownerID = scanlog.nextLine();

            System.out.print("Enter Log In Password : ");
            String ownerPass = scanlog.nextLine();

            for (int i = 1; i <= ListStaff.getLength(); i++) {
                if (ownerID.equals(ListStaff.getEntry(i).getStaff_ID()) && ownerPass.equals(ListStaff.getEntry(i).getEntryKey())) {
                    session = 1;
                } else {
                    System.out.print("The Log In ID or Password is not matched !\n\n");
                    session = 0;
                }
            }
        } while (session != 1);

        if (session == 1) {
            mainMenu();
        }
    }

    public static void mainMenu() {
        userRecords();
        records(); //payment records
        Scanner input = new Scanner(System.in);
        OrderModule ordering = new OrderModule();
        ordering.orderRecord();
        ItemModule item = new ItemModule();
        int index;
        do {
            System.out.println("------------------------------------------------");
            System.out.println("\t\tWelcome to 063 Cafe");
            System.out.println("------------------------------------------------");
            System.out.println("---------------------Menu-----------------------");
            System.out.println("|\t\t1. Order");
            System.out.println("|\t\t2. Payment");
            System.out.println("|\t\t3. Item");
            System.out.println("|\t\t4. Staff");
            System.out.println("|\t\t5. Member");
            System.out.println("|\t\t0. Shut down");
            System.out.println("------------------------------------------------\n");
            System.out.printf("  Please enter the index (1-5): ");
            while (!input.hasNextInt()) {
                System.out.println("That's not a number! \nPlease enter again: ");
                input.next(); // this is important!
            }
            index = input.nextInt();
            input.nextLine();
            switch (index) {
                case 1:
                    ordering.orderMenu();
                    break;
                case 2:
                    selectAction();
                    break;
                case 3:
                    item.itemMenu();
                    break;
                case 4:
                    menuStaff();
                    break;
                case 5:
                    menuMember();
                    break;
                case 0:
                    System.out.println("Thank you for using 603 Cafe POS System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error! Please select between 1 - 5!");
            }
        } while (index != 0);
    }

    // Menu of Payment Module
    public static void selectAction() {
        Scanner scanA = new Scanner(System.in);
        int action = 0;
        boolean selectIsDigit = false;
        do {
            do {
                try {
                    System.out.println("\t\t\t\t                Payment Module ");
                    System.out.println("\t\t\t\t _______________________________________");
                    System.out.println("\t\t\t\t| No. |            Actions              |");
                    System.out.println("\t\t\t\t|  1  | Make Payment                    |");
                    System.out.println("\t\t\t\t|  2  | Display Records                 |");
                    System.out.println("\t\t\t\t|  3  | Edit Records                    |");
                    System.out.println("\t\t\t\t|  0  | Back to Main Menu               |");
                    System.out.println("\t\t\t\t|_____|_________________________________|\n");
                    System.out.print("Please enter a number: ");
                    action = scanA.nextInt();
                    selectIsDigit = true;
                } catch (Exception e) {
                    System.out.println("Please enter an integer number and try again!");
                    scanA.nextLine();
                }
            } while (!selectIsDigit);
            switch (action) {
                case 1:
                    if (orderLine.getFirst() == null) {
                        System.out.println("\nNo Order has been created. Please back to main menu and place order.\n");
                        break;
                    } else {
                        paymentSystem();
                    }
                    break;
                case 2:
                    displayRecords();
                    break;
                case 3:
                    modifyPayment();
                    break;
                case 0:
                    break;
                default:
                    errorMessage();
            }
        } while (action != 0);
    }
}
