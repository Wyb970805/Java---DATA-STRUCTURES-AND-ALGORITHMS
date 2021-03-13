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
public class OrderModule {
    char exit = 'a';
        
        int orderNum = 1000, tbNum = 0;
        String orderList = null;
        Scanner scanner = new Scanner(System.in);
        static QueueInterface<Order> orderLine = new CircularLineQueue<>();
        
        public void placeOrder(){
            System.out.println("Welcome!!");
        System.out.println("New Order (Y/N)?");
        char order = scanner.nextLine().charAt(0);
        while(order == 'Y' || order == 'y'){
            orderNum++;
            
            System.out.print("Dine in - D, Take Away - T : ");
            char orderType = scanner.nextLine().charAt(0);
            
            orderType = Character.toUpperCase(orderType);
            
            if(orderType !='D' && orderType != 'T'){
                System.out.println("Not to create order? Will go back to main menu.");
                break;
            }else{
                if(orderType == 'D'){
                    System.out.print("Table Number: ");
                    tbNum = scanner.nextInt();
                    scanner.nextLine();
                }else{
                    tbNum = 0;
                    System.out.println("This is a take away order.");
                }
                Order od = new Order(orderNum,orderType, tbNum);
                boolean added = orderLine.addToQueue(od);
                if(added == true){
                    System.out.println("Order created.");
                }else{
                    System.out.println("ERROR! Order haven't created.");
                }
                
            }
            
            
        
        System.out.println("Next Order (Y/N)?");
        order = scanner.nextLine().charAt(0);
        }
        
        System.out.println("============================");
        System.out.println("Show All Order");
        System.out.println(orderLine); 
        //System.out.println("Show first order");
        //System.out.println(orderLine.getFirst());
        }
        
    
}

