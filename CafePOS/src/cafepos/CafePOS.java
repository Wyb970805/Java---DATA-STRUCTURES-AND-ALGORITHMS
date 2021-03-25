/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import Entity.*;
import ADT.*;
import java.util.Scanner;
import static cafepos.EditPayment.modifyPayment;
import static cafepos.OrderModule.orderLine;
import static cafepos.RecordPayment.*;

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
        mainMenu();
        OrderModule ordering = new OrderModule();
        ordering.orderMenu();
    }

    public static void mainMenu() {
        Scanner input = new Scanner(System.in);
        OrderModule ordering = new OrderModule();
        System.out.println("-------------------");
        System.out.println("Welcome to 063 Cafe");
        System.out.println("-------------------");

        System.out.println("--------Menu-------");
        System.out.println("1. Order");
        System.out.println("2. Payment");
        System.out.println("3. Item");
        System.out.println("4. Staff");
        System.out.println("0. Shut down");
        System.out.printf("Please enter the index (1-4): ");
        while (!input.hasNextInt()) {
            System.out.println("That's not a number! \nPlease enter again: ");
            input.next(); // this is important!
        }
        int index = input.nextInt();
        input.nextLine();
        switch (index) {
            case 1:
                ordering.orderMenu();
                break;
            case 2:
                selectAction();
                break;
            case 3:

                break;
            case 4:

                break;
            case 0:
                System.out.println("Thank you. See you tomorrow");
                break;
            default:
                System.out.println("Error! Please select between 1 - 4!");
        }
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
                    System.out.println("\t\t\t\t|  0  | Exit                            |");
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
                    records();
                    if (orderLine.getFirst() == null) {
                        System.out.println("\nNo Order has been stored. Please Order.\n");
                        OrderModule ordering = new OrderModule();
                        ordering.orderMenu();
                    }
                    paymentSystem();
                    break;
                case 2:
                    records();
                    displayRecords();
                    yesNo();
                    break;
                case 3:
                    records();
                    modifyPayment();
                    yesNo();
                    break;
                case 0:
                    break;
                default:
                    errorMessage();
            }
        } while (action < 0 || action > 3);
    }

}
