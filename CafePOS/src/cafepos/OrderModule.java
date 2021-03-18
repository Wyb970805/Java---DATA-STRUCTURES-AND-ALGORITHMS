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
        boolean error = false;
        System.out.println("New Order (Y/N)?");
        char order = sc.nextLine().charAt(0);
        while(order == 'Y' || order == 'y'){
            
        //    System.out.print("Member ID: ");
        //    String mid = sc.nextLine();
            do{
            System.out.print("Dine in - D, Take Away - T (q to exit): ");
            char orderType = sc.nextLine().charAt(0);
            orderType = Character.toUpperCase(orderType);
            if(orderType == 'D'){
                    System.out.print("Table Number: ");
                    tbNum = sc.nextInt();
                    sc.nextLine();
                    orderNum++;
                    System.out.println("Select item");
                    System.out.println("=======================");
                    ItemOrderModule orderList = new ItemOrderModule();
                    boolean created = orderList.createOrderList();
                    if(created == true){
                        Order od = new Order(orderNum,orderType, tbNum);
                        boolean added = orderLine.addToQueue(od);
                        if(added == true){
                            System.out.println("Order created.");
                            System.out.println(od);
                            System.out.println("==========" + orderLine);
                        }else{
                            System.out.println("ERROR! Order haven't created.");
                        }
                    }else{
                        System.out.println("Error");
                    }
                    
                
                }else if(orderType == 'T'){
                    tbNum = 0;
                    System.out.println("This is a take away order.");
                    orderNum++;
                    Order od = new Order(orderNum,orderType, tbNum);
                boolean added = orderLine.addToQueue(od);
                if(added == true){
                    System.out.println("Order created.");
                    System.out.println(od);
                    System.out.println("==========" + orderLine);
                }else{
                    System.out.println("ERROR! Order haven't created.");
                }
                
                }else if(orderType == 'Q'){
                    System.out.println("Ok. Will go back.");
                    break;
                }else{
                    System.out.println("Please enter D or T. q to exit.");
                    error = true;
                }
            }while(error == true);
            
            
            
        
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
                     //   editItem();
                        break;
                    case 2:
                     //   editMember();
                        break;
                    case 3:
                        editTableNum();
                        break;
                    case 4:
                        editOrderType();
                        break;
                    default:
                        System.out.println("Only 1 - 4 are offered to change.");
                }
                
            }
        }
        
        public void editTableNum(){
            boolean error = false;
            if(!orderLine.isEmpty()){
                do{
            Order first = orderLine.getFirst();
            System.out.println("Current table number = " + first.getTbNum());
            System.out.println("New table (0 to cancel): ");
            int newTable = sc.nextInt();
            sc.nextLine();
            if(newTable >= 1 && newTable <= 20){
                Order editedOrder = orderLine.getFirst();
                editedOrder.setTableNum(newTable);
                boolean result = orderLine.setFirst(editedOrder);
                if(result == true){
                System.out.println(orderLine.getFirst());
                
                }else{
                    System.out.println("Changes has not apply.");
                }
                
            }
            else if(newTable==0){
              break;  
            }else{
                System.out.println("Please enter table number between 01-20.");
                error = true;
            }
            
            }while(error==true);
            }else{
                System.out.println("No order in queue.");
                
            }
            
        }
        
        public void editOrderType(){
            boolean error = false;
            if(!orderLine.isEmpty()){
                do{
            Order first = orderLine.getFirst();
            System.out.println("Current Order Type = " + first.getOrderType());
            System.out.println("Change to Dine in (D) or Take away (T) (q to cancel): ");
            char newOType = sc.nextLine().charAt(0);
            newOType = Character.toUpperCase(newOType);
            if(newOType == 'D' || newOType == 'T'){
                Order editedOrder = orderLine.getFirst();
                editedOrder.setOrderType(newOType);
                boolean result = orderLine.setFirst(editedOrder);
                if(result == true){
                System.out.println(orderLine.getFirst());
                }else{
                    System.out.println("Changes has not apply.");
                }
            }
            else if(newOType=='q'){
              break;  
            }else{
                System.out.println("Please enter D for Dine in or T for Take Away.");
                error = true;
            }
            
            }while(error==true);
            
        }
            else{
                System.out.println("No order in queue.");
                
            }
            //return changed;
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

