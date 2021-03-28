/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.ArraySet;
import Entity.Item;
import Entity.IngredientsAdd;
import java.util.Scanner;
import Entity.Ingredient;
import static cafepos.CafePOS.mainMenu;

/**
 *
 * @author User10
 */
public class ItemModule {
    public static ArraySet<Item> ItemSet = new ArraySet<Item>();
    public static ArraySet<Ingredient> IngredientSet = new ArraySet<Ingredient>();
    public static ArraySet<IngredientsAdd> IngredientList = new ArraySet<IngredientsAdd>();
    //public ArrayList<ItemOrder> OrderList = new ArrayList<ItemOrder>();
    
   Scanner scan = new Scanner(System.in);
   
   public void itemMenu(){
       
       // add exist item into adt set
       ItemSet.add(new Item("F1001","Chicken Fried Rice",5.0,'F'));
       ItemSet.add(new Item("B1001","Pineapple Juice",3.5,'B'));
       IngredientSet.add(new Ingredient("S1001","Fish",21,6));
       
       int select;
       do{
           System.out.println("\n ----------------------------------- ");
           System.out.println("|----------- Item Module ------------|");
           System.out.println("|------------------------------------|");
           System.out.println("|  1. Enter new Item                 |");
           System.out.println("|  2. Edit Item                      |");
           System.out.println("|  3. Delete Item                    |");
           System.out.println("|  4. Display All Item               |");
           System.out.println("|  0. Exit                           |");
           System.out.println(" ----------------------------------- ");
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
        //String itemIngredient;
        double itemPrice;
        int select;
        char category = 0;
        boolean create = false;
        char nextIngredient;
        Ingredient ingredientPlus = null;
        
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        Scanner scan3 = new Scanner(System.in);
        Scanner scan4 = new Scanner(System.in);
        
        System.out.print("Enter Item Id: ");
        itemId = scan.nextLine();
        
        System.out.print("Enter Item Name: ");
        itemName = scan1.nextLine();
        
        System.out.print("Enter Item Price: ");
        itemPrice = scan2.nextDouble();
        
        do{
        IngredientModule.displayStock();
        System.out.print("Enter IngredientID to add ingredient: ");
        String itemIngredient = scan3.nextLine();
        //scan.nextLine();
        //Ingredient itemsOrder = null;
        for (int i = 1; i <= IngredientSet.getNum(); i++) {
            if (IngredientSet.getEntry(i).getStockId().equals(itemIngredient)) {
                    ingredientPlus = IngredientSet.getEntry(i);
                    IngredientsAdd plus = new IngredientsAdd(ingredientPlus);
                create = IngredientList.add(plus);
                System.out.println("Ingredient addded!");
                //System.out.print("\n" + IngredientList);
                //ItemSet.add(new Item(itemId, itemName, itemPrice,itemsOrder,category));
                
            } else {
                System.out.println("Ingredient Not Found!");
            }
                    }
        System.out.print("More ingredient? (Y/N): ");
            nextIngredient = scan.nextLine().charAt(0);
            nextIngredient = Character.toUpperCase(nextIngredient);
        } while (nextIngredient == 'Y');
                    
                    
        
        do{
            System.out.println("\n ------------------------------------ ");
            System.out.println("|---------- Choose category ---------|");
            System.out.println("|------------------------------------|");
            System.out.println("|  1. 'F'= Food                      |");
            System.out.println("|  2. 'B' = Beverage                 |");
            System.out.println(" ------------------------------------ ");
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
            else{
                System.out.println("Invalid number! Please re-enter: ");
            }
        }while(select!=1 && select!=2);
        
            System.out.print("Item addded successfully!");
   
        ItemSet.add(new Item(itemId, itemName, itemPrice, category));
        
    }
   
   public void editItem() {
        
        String itemID;
        String editName;
        double editPrice;
        String editIngredient;
        int select;
        char confirm = 0;
        boolean found = false;
        boolean created = false;
        char nextItem;
        Scanner scan = new Scanner(System.in);
        Scanner scanName = new Scanner(System.in);
        Scanner scanPrice = new Scanner(System.in);
        Scanner scanCat = new Scanner(System.in);
        Scanner scanIngredient = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);
        
        System.out.print("Enter ItemID to modify item: ");
        itemID = scan.nextLine();
        
        for(int i = 1; i <= ItemSet.getNum(); i++) {
            
            if(ItemSet.getEntry(i).getItemId().equals(itemID)) {
                System.out.println("\n--------------------- Edit Item -----------------------");
                System.out.print(ItemSet.getEntry(i).toString());
                System.out.print("Ingredients: ");
                System.out.println(IngredientList);
                System.out.println("-------------------------------------------------------");
                System.out.print("Do you confirm edit this item? (Y/N): ");
                confirm = scan.nextLine().charAt(0);
                if(confirm == 'Y' || confirm == 'y'){
                System.out.println("\n ------------------------------------ ");
                System.out.println("|------------- Edit Item ------------|");
                System.out.println("|------------------------------------|");
                System.out.println("|  1. Edit Item Name:                |");
                System.out.println("|  2. Edit Item Price:               |");
                System.out.println("|  3. Edit Item Ingredient:          |");
                System.out.println("|  4. Edit Item Category:            |");
                System.out.println("|  0. Exit:                          |");
                System.out.println(" ------------------------------------ ");
                System.out.print("Enter number (1-4): ");
                while (!scan.hasNextInt()) {
            System.out.print("Input invalid! Please re-enter: ");
            scan.next();
            }
                select = scan.nextInt();

                if (select == 1) {
                    
                    System.out.println("\nOld Item Name: " + ItemSet.getEntry(i).getItemName() + "");//.toString()
                    System.out.print("Enter New Item Name: ");
                    editName = scanName.nextLine();
                    
                    ItemSet.getEntry(i).setItemName(editName);
                    System.out.println("This item successfully changed!");
                }
                else if (select == 2) {
                    System.out.println("\nOld Item Price: " + ItemSet.getEntry(i).getPrice() + "");
                    System.out.print("Enter New Item Price: ");
                    editPrice = scanPrice.nextDouble();
                    
                    ItemSet.getEntry(i).setPrice(editPrice);
                    System.out.println("This item successfully changed!");
                }
                else if (select == 3) {
                    //IngredientSet.get
                    boolean create = false;
                    Ingredient ingredientPlus = null;
                    System.out.println("\nOld Item Ingredient: " + IngredientList + "");
                    do{
                    System.out.print("Enter New IngredientID to edit ingredient: ");
                    String itemIngredient = scan1.nextLine();
        
                    for (int a = 1; a <= IngredientSet.getNum(); a++) {
            if (IngredientSet.getEntry(a).getStockId().equals(itemIngredient)) {
                    ingredientPlus = IngredientSet.getEntry(a);
                    IngredientsAdd plus = new IngredientsAdd(ingredientPlus);
                create = IngredientList.add(plus);
                System.out.println("Ingredient addded!");
                System.out.print("\n" + IngredientList);
                //ItemSet.add(new Item(itemId, itemName, itemPrice,itemsOrder,category));
                
            } else {
                System.out.println("Ingredient Not Found!");
            }
                    }
        System.out.print("More item?");
            nextItem = scan.nextLine().charAt(0);
            nextItem = Character.toUpperCase(nextItem);
        } while (nextItem == 'Y');
                    
                    }
                   //return created;
                    //ItemSet.getEntry(i).setPrice(editPrice);
                    //System.out.println("This item successfully changed!");
                
                else if (select == 4) {
                    int editCategory;
                    System.out.println("\nOld Item Category: " + ItemSet.getEntry(i).getCategory() + "");
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
        System.out.println("\n------------------------------------");
        System.out.println("----------- Delete Item ------------");
        System.out.println("------------------------------------");
        System.out.print(ItemSet.getEntry(i).toString() + "");
        System.out.println("------------------------------------");
        System.out.print("Do you confirm delete this item? (Y/N): ");
        confirm = scan.nextLine().charAt(0);
        
        if(confirm == 'Y' || confirm == 'y'){
            ItemSet.remove(i);
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
        System.out.println("\n ------------------------------------");
        System.out.println("|---------- Total " +ItemSet.getNum()+ " Items -----------|");
        System.out.println(" ------------------------------------");
        if (!ItemSet.isEmpty()) {
        for(int i = 1; i <= ItemSet.getNum(); i++){
            System.out.print(ItemSet.getEntry(i).toString());
            System.out.print("Ingredients: ");
            System.out.println(IngredientList+"\n");
        }
        }else
            System.out.println("The Item List is Empty!");
    }
   
   
}