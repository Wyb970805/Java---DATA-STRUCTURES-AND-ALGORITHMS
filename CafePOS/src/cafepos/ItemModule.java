/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.ArraySet;
import Entity.Item;
import java.util.Scanner;
import Entity.Ingredient;

/**
 *
 * @author Wen
 */
public class ItemModule {
    public static ArraySet<Item> ItemSet = new ArraySet<Item>();
    
   Scanner scan = new Scanner(System.in);
   
   public void itemMenu(){
       int select;
       do{
           System.out.println("----------- Item -----------");
           System.out.println("----------------------------");
           System.out.println("1. Enter new Item");
           System.out.println("2. Edit Item");
           System.out.println("3. Delete Item");
           System.out.println("4. Display All Item");
           System.out.println("0. Exit");
           System.out.printf("Enter number (1-4): ");
           while (!scan.hasNextInt()) {
            System.out.println("Input invalid! Please re-enter: ");
            scan.next();
            }
        select = scan.nextInt();
        scan.nextLine();
        switch(select){
            case 1:
                addItem();
                break;
            case 2:
                editItem();
                break;
            case 3:
                //deleteItem();
                break;
            case 4:
                displayItem();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid number! Please re-enter: ");            
        }
        }while(select != 0 );
    }
   
   public static void addItem() {
        
        String itemId, itemName;
        double itemPrice;
        int select;
        char category;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter Item Id: ");
        itemId = scan.nextLine();
        
        System.out.print("Enter Item Name: ");
        itemName = scan.nextLine();
        
        System.out.print("Enter Item Price: ");
        itemPrice = scan.nextDouble();
        
        //do {
            System.out.print("-------- Choose category --------");
            System.out.print("1. 'F'= Food");
            System.out.print("2. 'B' = Beverage");
            System.out.print("Enter number (1-2): ");
            while (!scan.hasNextInt()) {
            System.out.println("Input invalid! Please re-enter: ");
            scan.next();
            }
            select = scan.nextInt();
            
            if (select == 1) {
                category = 'F';
            }
            else if (select == 2) {
                category = 'B';
            }
            
        //ItemSet.add(new Item(itemId, itemName, itemPrice, category));
        
    }
   
   public static void editItem() {
        
        String itemID;
        int select;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter ItemID to modify item: ");
        itemID = scan.nextLine();
        
        for(int i = 1; i <= ItemSet.getNum(); i++) {
            
            if(ItemSet.getEntry(i).getItemId().equals(itemID)) {
                System.out.print(ItemSet.getEntry(i).toString() + " ");
                System.out.print("1. Edit Item Name: ");
                System.out.print("2. Edit Item Price: ");
                System.out.print("3. Edit Item Ingredient: ");
                System.out.print("4. Edit Item Category: ");
                System.out.print("7. Cancel Modifying or Switch Staff: ");
                System.out.print("Enter number (1-4): ");
                select = scan.nextInt();

                if (select == 1) {
                    System.out.print("Old Item Name ( " + ItemSet.getEntry(i).getItemName().toString() + " ).");
                    System.out.print("Enter New Item Name: ");
                    String editName = scan.nextLine();
                    
                    ItemSet.getEntry(i).setItemName(editName);
                }
                else if (select == 2) {
                    //System.out.print("Old Item Price( " + ItemSet.getEntry(i).getPrice().toString() + " ).");
                    System.out.print("Enter New Item Price: ");
                    double editPrice = scan.nextDouble();
                    
                    ItemSet.getEntry(i).setPrice(editPrice);
                }
                else if (select == 4) {
                    //System.out.print("The old staff role ( " + ItemSet.getEntry(i).getCategory().toString() + " ).");
                    System.out.print("Enter New Item Category: ");
                    //char editCategory = scan.nextInt();
                    
                    //ItemSet.getEntry(i).setCategory(editCategory);
                }
                else {
                    
                }
    
            }
        }
        
    }
   public static void displayItem() {
        System.out.print(ItemSet.getNum());
        
        for(int i = 1; i <= ItemSet.getNum(); i++){
            System.out.println(ItemSet.getEntry(i).toString());
        
        }
    }
}
