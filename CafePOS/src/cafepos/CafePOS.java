/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import Entity.*;
import ADT.*;
import java.util.Scanner;

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
        
       // OrderModule ordering = new OrderModule();
      //  ordering.orderMenu();
        mainMenu();
        
        
        
        
        
        
        
    }
    
    public static void mainMenu(){
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
        switch(index){
            case 1:
                ordering.orderMenu();
                break;
            case 2:
                
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
    
}
