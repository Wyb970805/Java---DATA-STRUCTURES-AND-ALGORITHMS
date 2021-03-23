/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;


import Entity.ItemOrder;
import java.util.Scanner;

/**
 *
 * @author Wen
 */
public class ItemOrderModule {
   ItemOrder items = new ItemOrder();
   Scanner sc = new Scanner(System.in);
   
   public void itemOrderMenu(){
       int choice;
       do{
           System.out.println("--------Item Order --------");
           System.out.println("----------------------------");
           System.out.println("1. Add Order item");
           System.out.println("2. Modify Order item");
           System.out.println("3. Remove Order item");
           System.out.println("4. Show all ordered item");
           System.out.println("0. Exit");
           System.out.printf("Please enter the index (1-4): ");
           while (!sc.hasNextInt()) {
                System.out.println("That's not a number! \nPlease enter again: ");
                sc.next(); // this is important!
            }
            choice = sc.nextInt();
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
                case 0:
                    break;
                default:
                    System.out.println("Error! Please select between 1 - 5!");
                        
            }
            }while(choice != 0 );
       
   }
   
   public boolean createOrderList(){
       
       boolean created = false ;
       System.out.println("Please enter the item code (): ");
       while (!sc.hasNext("[A-Za-z][/d](4)")) {
       System.out.println("The format is wrong. \nPlease enter again: ");
       sc.next();
       String item = sc.nextLine();
       
}
       
       return created;
   }
   
   
   public void modifyItemOrder(){
       
   }
   
   public void removeItemOrder(){
       
   }
   
   
   
}
