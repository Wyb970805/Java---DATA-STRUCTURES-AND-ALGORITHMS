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
import static cafepos.CafePOS.mainMenu;

/**
 *
 * @author User10
 */
public class ItemModule {
    public static ArraySet<Item> ItemSet = new ArraySet<Item>();
    
   Scanner scan = new Scanner(System.in);
   
   public void itemMenu(){
       
       // add exist item into adt set
       ItemSet.add(new Item("F1001","food1",5.0,'F'));
       ItemSet.add(new Item("F1002","food2",7.5,'F'));
       ItemSet.add(new Item("B1001","drink1",5.0,'B'));
       ItemSet.add(new Item("B1002","drink2",3.5,'B'));
       
       int select;
       do{
           System.out.println("----------- Item Module -----------");
           System.out.println("-----------------------------------");
           System.out.println("1. Enter new Item");
           System.out.println("2. Edit Item");
           System.out.println("3. Delete Item");
           System.out.println("4. Display All Item");
           System.out.println("0. Exit");
           System.out.printf("Enter number (1-4): ");
           while (!scan.hasNextInt()) {
            System.out.print("Input invalid! Please re-enter: ");
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
                deleteItem();
                break;
            case 4:
                displayItem();
                break;
            case 0:
                mainMenu();
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
        char category = 0;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter Item Id: ");
        itemId = scan.nextLine();
        
        System.out.print("Enter Item Name: ");
        itemName = scan.nextLine();
        
        System.out.print("Enter Item Price: ");
        itemPrice = scan.nextDouble();
        
        //do {
            System.out.println("-------- Choose category --------");
            System.out.println("---------------------------------");
            System.out.println("1. 'F'= Food");
            System.out.println("2. 'B' = Beverage");
            System.out.print("Enter number (1-2): ");
            while (!scan.hasNextInt()) {
            System.out.print("Input invalid! Please re-enter: ");
            scan.next();
            }
            select = scan.nextInt();
            
            if (select == 1) {
                category = 'F';
            }
            else if (select == 2) {
                category = 'B';
            }
            
        ItemSet.add(new Item(itemId, itemName, itemPrice, category));
        
    }
   
   public void editItem() {
        
        String itemID;
        String editName;
        double editPrice;
        int select;
        char confirm = 0;
        Scanner scan = new Scanner(System.in);
        Scanner scanName = new Scanner(System.in);
        Scanner scanPrice = new Scanner(System.in);
        Scanner scanCat = new Scanner(System.in);
        
        System.out.print("Enter ItemID to modify item: ");
        itemID = scan.nextLine();
        
        for(int i = 1; i <= ItemSet.getNum(); i++) {
            
            if(ItemSet.getEntry(i).getItemId().equals(itemID)) {
                System.out.println("\n--------------------- Edit Item -----------------------");
                System.out.println(ItemSet.getEntry(i).toString() + " ");
                System.out.println("-------------------------------------------------------");
                System.out.print("Do you confirm edit this item? (Y/N): ");
                confirm = scan.nextLine().charAt(0);
                if(confirm == 'Y' || confirm == 'y'){
                System.out.println("1. Edit Item Name: ");
                System.out.println("2. Edit Item Price: ");
                System.out.println("3. Edit Item Ingredient: ");
                System.out.println("4. Edit Item Category: ");
                System.out.println("0. Exit: ");
                System.out.print("Enter number (1-4): ");
                select = scan.nextInt();

                if (select == 1) {
                    
                    System.out.println("Old Item Name: " + ItemSet.getEntry(i).getItemName() + "");//.toString()
                    System.out.print("Enter New Item Name: ");
                    editName = scanName.nextLine();
                    
                    ItemSet.getEntry(i).setItemName(editName);
                    System.out.println("This item successfully changed!");
                }
                else if (select == 2) {
                    System.out.println("Old Item Price: " + ItemSet.getEntry(i).getPrice() + "");
                    System.out.print("Enter New Item Price: ");
                    editPrice = scanPrice.nextDouble();
                    
                    ItemSet.getEntry(i).setPrice(editPrice);
                    System.out.println("This item successfully changed!");
                }
                else if (select == 4) {
                    int editCategory;
                    System.out.println("Old Item Category: " + ItemSet.getEntry(i).getCategory() + "");
                    System.out.print("1. 'F'= Food\n");
                    System.out.print("2. 'B' = Beverage\n");
                    System.out.print("Enter New Item Category (1-2): ");
                    editCategory = scanCat.nextInt();
                    
                    char category = 0;
            
            if (editCategory == 1) {
                category = 'F';
            }
            else if (editCategory == 2) {
                category = 'B';
            }
                    ItemSet.getEntry(i).setCategory(category);
                    System.out.println("This item successfully changed!");
                }
                else{
                    itemMenu();
                }
                
    
            }else{
                    itemMenu();
                }
        }
        }
    }
   
   public void deleteItem() {
        
        String itemID;
        char confirm;
        //Item delete = null;
        Scanner scan = new Scanner(System.in);
        
        
        System.out.print("Enter ItemID to delete the item: ");
        itemID = scan.nextLine();
        
        
        for(int i = 1; i <= ItemSet.getNum(); i++) {
            
        if(ItemSet.getEntry(i).getItemId().equals(itemID)){
        System.out.println("\n------------------- Delete Item -------------------");
        System.out.println(ItemSet.getEntry(i).toString() + "");
        System.out.println("---------------------------------------------------");
        System.out.print("Do you confirm delete this item? (Y/N): ");
        confirm = scan.nextLine().charAt(0);
        
        if(confirm == 'Y' || confirm == 'y'){
            ItemSet.delete(i);
            System.out.println("This item have been deleted!");
        }
        else{
            itemMenu();
        }
        }
        //else{
           // System.out.print("Invalid Item ID! Please re-enter: ");
           // scan.next();
        //}
        }
   }
   
   public static void displayItem() {
        System.out.println("");
        System.out.println("---------- Total " +ItemSet.getNum()+ " Items ----------");
        System.out.println("-----------------------------------");
        if (!ItemSet.isEmpty()) {
        for(int i = 1; i <= ItemSet.getNum(); i++){
            System.out.println(ItemSet.getEntry(i).toString());
        
        }
        }else
            System.out.println("The Item List is Empty!");
    }
}