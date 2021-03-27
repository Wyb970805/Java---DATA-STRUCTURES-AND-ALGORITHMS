/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import Entity.Member;
import Entity.Staff;
import java.util.Scanner;
import static cafepos.EditPayment.modifyPayment;
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
        userRecords();
        records(); //payment records
        mainMenu();
    }

    public static void userRecords() {
        ListStaff.add(new Staff("wegg", "rehteh", "etrhh", "ertehe", "roles", 0, 1000, 1500));
        ListStaff.add(new Staff("wegg1", "rehteh1", "etrhh1", "ertehe1", "roles", 0, 1000, 1500));
        ListStaff.add(new Staff("wegg2", "rehteh2", "etrhh2", "ertehe2", "roles", 0, 1000, 1500));
        ListMember.add(new Member("name", "phoneNo", "emailAddress", 10));
        ListMember.add(new Member("name1", "phoneNo1", "emailAddress1", 10));
        ListMember.add(new Member("name2", "phoneNo2", "emailAddress2", 10));
    }

    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        OrderModule ordering = new OrderModule();
        ItemModule item = new ItemModule();
        int index;
        do {
            System.out.println("-------------------");
            System.out.println("Welcome to 063 Cafe");
            System.out.println("-------------------");
            System.out.println("--------Menu-------");
            System.out.println("1. Order");
            System.out.println("2. Payment");
            System.out.println("3. Item");
            System.out.println("4. Staff");
            System.out.println("5. Member");
            System.out.println("0. Shut down");
            System.out.printf("Please enter the index (1-4): ");
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
                    System.out.println("Thank you. See you tomorrow");
                    break;
                default:
                    System.out.println("Error! Please select between 1 - 4!");
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
