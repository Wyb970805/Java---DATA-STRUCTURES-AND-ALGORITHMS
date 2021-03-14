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
    Scanner sc = new Scanner(System.in);
    static QueueInterface<Order> orderLine = new CircularLineQueue<>();
    static QueueInterface<Order> completedOrder = new CircularLineQueue<>();
        
    public void orderMenu(){
        int choice;
        do{
        System.out.println("Hi, welcome!");
        System.out.println("Order");
        System.out.println("==========");
        System.out.println("1. Place Oredr");
        System.out.println("2. Update Status");
        System.out.println("3. Edit Order");
        System.out.println("4. Remove Order");
        System.out.println("5. Show all order");
        System.out.println("0. Exit");
        choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                placeOrder();
                break;
            case 2:
                updateOrderStatus();
                break;
            case 3:
                editOrder();
                break;
            case 4:
                removeOrder();
                break;
            case 5:
                showOrder();
                break;
            case 0:
                break;
            default:
                System.out.println("Error! Please select between 1 - 4!");
                        
        }
        }while(choice != 0 );
    }
    
    public void placeOrder(){
        System.out.println("Welcome!!");
        System.out.println("New Order (Y/N)?");
        char order = sc.nextLine().charAt(0);
        while(order == 'Y' || order == 'y'){
            orderNum++;
        //    System.out.print("Member ID: ");
        //    String mid = sc.nextLine();
            System.out.print("Dine in - D, Take Away - T : ");
            char orderType = sc.nextLine().charAt(0);
            
            orderType = Character.toUpperCase(orderType);
            
            if(orderType !='D' && orderType != 'T'){
                System.out.println("Not to create order? Will go back to main menu.");
                break;
            }else{
                if(orderType == 'D'){
                    System.out.print("Table Number: ");
                    tbNum = sc.nextInt();
                    sc.nextLine();
                }else{
                    tbNum = 0;
                    System.out.println("This is a take away order.");
                }
                
                Order od = new Order(orderNum,orderType, tbNum);
                boolean added = orderLine.addToQueue(od);
                if(added == true){
                    System.out.println("Order created.");
                    System.out.println(od);
                    System.out.println("==========" + orderLine);
                }else{
                    System.out.println("ERROR! Order haven't created.");
                }
                
            }
            
            
        
        System.out.println("Next Order (Y/N)?");
        order = sc.nextLine().charAt(0);
        }
        
        
        //System.out.println("Show first order");
        //System.out.println(orderLine.getFirst());
        }
        
        
        
        
        public void removeOrder(){
            Order removed = null;
            System.out.println("Are you sure you want to cancel the first order(Y/N)?");
            char confirm = sc.nextLine().charAt(0);
            if(confirm == 'Y' || confirm == 'y'){
                if(!orderLine.isEmpty()){
                System.out.println(orderLine.getFirst());
                System.out.println("Are you sure you want to cancel this order(Y/N)?");
                char con = sc.nextLine().charAt(0);
                if(con == 'Y' || con == 'y')
                removed = orderLine.removeFQueue();
                System.out.println(removed + "------ Already removed.");
                }else
                    System.out.println("There is no order in queue.");
                
            }
            
           
            
            
        }
        
        public void updateOrderStatus(){
            if(!orderLine.isEmpty()){
                System.out.println("Update the Order to the completed status.");
                Order completed = orderLine.getFirst();
                System.out.println(completed);
                completedOrder.addToQueue(completed);
                orderLine.removeFQueue();
                System.out.println("Your order is completed.");
            }else{
                System.out.println("There is no order in queue.");
            }
           
        }
        
        public void editOrder(){
            if(!orderLine.isEmpty()){
                System.out.println("Any changes of the order on? ");
                System.out.println("1. Ordered Item");
                System.out.println("2. Member ID");
                System.out.println("3. Table Number");
                System.out.println("4. Order Type");
                int choice = sc.nextInt();
                sc.nextLine();
                switch(choice){
                    case 1:
                        
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Only 1 - 4 are offered to change.");
                }
                
            }
        }
        
        
        
        public void showOrder(){
            int choice;
            do{
            System.out.println("\n\n =========================");
            System.out.println("1. Show Order Queue");
            System.out.println("2. Show Completed Order");
            System.out.println("0. Back to preious");
            System.out.println("=========================");
            choice = sc.nextInt();
            sc.nextLine();
            switch(choice){
                case 1:
                    showOrderQueue();
                    break;
                case 2:
                    showCompletedQueue();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please select 1 pr 2, 0 to back.");
            }
            }while(choice != 0);
        }
        
        private void showOrderQueue(){
            if(!orderLine.isEmpty()){
                System.out.println("============================");
                System.out.println("Show All Order");
                System.out.println("============================");
                System.out.println(orderLine); 
            }else{
                System.out.println("There is no order in queue");
            }
        }
        private void showCompletedQueue(){
            if(!completedOrder.isEmpty()){
                System.out.println("============================");
                System.out.println("Show All Completed Order");
                System.out.println("============================");
                System.out.println(completedOrder); 
            }else{
                System.out.println("There is no completed order.");
            }
        }
    
}

