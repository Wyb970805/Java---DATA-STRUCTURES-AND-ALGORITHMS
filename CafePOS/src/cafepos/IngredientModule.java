/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.ArraySet;
import java.util.Scanner;
import Entity.Ingredient;
import static cafepos.CafePOS.mainMenu;
/**
 *
 * @author User10
 */
public class IngredientModule {
    
    public static ArraySet<Ingredient> IngredientSet = new ArraySet<Ingredient>();
    
    Scanner scan = new Scanner(System.in);
   
   public void ingredientMenu(){
       
       // add exist item into adt set
       IngredientSet.add(new Ingredient("S1001","food1",52,12,32));
       IngredientSet.add(new Ingredient("S1002","food2",72,32,54));
       IngredientSet.add(new Ingredient("N1001","drink1",5,76,23));
       IngredientSet.add(new Ingredient("N1002","drink2",3,34,23));
       
       int select;
       do{
           System.out.println("----------- Ingredient Module -----------");
           System.out.println("-----------------------------------------");
           System.out.println("1. Add new Stock");
           System.out.println("2. Edit Stock");
           System.out.println("3. Delete Stock");
           System.out.println("4. Display All Stock");
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
                addStock();
                break;
            case 2:
                editStock();
                break;
            case 3:
                deleteStock();
                break;
            case 4:
                displayStock();
                break;
            case 0:
                //mainMenu();
                break;
            default:
                System.out.println("Invalid number! Please re-enter: ");            
        }
        }while(select != 0 );
    }

   public static void addStock() {
        
        String stockId, stockName;
        int stockQuantity, stockLowQuantity, stockLowAllow;
        int select;
        char category = 0;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter Stock Id: ");
        stockId = scan.nextLine();
        
        System.out.print("Enter Stock Name: ");
        stockName = scan.nextLine();
        
        System.out.print("Enter Ingredient Quantity: ");
        stockQuantity = scan.nextInt();
        
        System.out.print("Enter Ingredient LowestQuanity: ");
        stockLowQuantity = scan.nextInt();
        
        System.out.print("Enter Ingredient LowestAllowed: ");
        stockLowAllow = scan.nextInt();
            
        IngredientSet.add(new Ingredient(stockId, stockName, stockQuantity, stockLowQuantity, stockLowAllow));
        
    }
   
   public void editStock() {
        
        String stockID;
        String editName;
        int editQuantity, editLowQuantity, editLowAllow;
        int select;
        char confirm = 0;
        Scanner scan = new Scanner(System.in);
        Scanner scanName = new Scanner(System.in);
        Scanner scanQuantity = new Scanner(System.in);
        Scanner scanLowQuantity = new Scanner(System.in);
        Scanner scanLowAllow = new Scanner(System.in);
        
        System.out.print("Enter StockID to modify stock: ");
        stockID = scan.nextLine();
        
        for(int i = 1; i <= IngredientSet.getNum(); i++) {
            
            if(IngredientSet.getEntry(i).getStockId().equals(stockID)) {
                System.out.println("--------------------- Edit Stock -----------------------");
                System.out.println(IngredientSet.getEntry(i).toString() + " ");
                System.out.println("-------------------------------------------------------");
                System.out.print("Do you confirm edit this item? (Y/N): ");
                confirm = scan.nextLine().charAt(0);
                if(confirm == 'Y' || confirm == 'y'){
                System.out.println("1. Edit Stock Name: ");
                System.out.println("2. Edit Stock Quantity: ");
                System.out.println("3. Edit Stock Lowest Quantity: ");
                System.out.println("4. Edit Stock Lowest Allowed: ");
                System.out.println("0. Exit: ");
                System.out.print("Enter number (1-4): ");
                select = scan.nextInt();

                if (select == 1) {
                    
                    System.out.println("Old Stock Name: " + IngredientSet.getEntry(i).getStockName() + "");//.toString()
                    System.out.print("Enter New Stock Name: ");
                    editName = scanName.nextLine();
                    
                    IngredientSet.getEntry(i).setStockName(editName);
                    System.out.println("This stock successfully changed!");
                }
                else if (select == 2) {
                    System.out.println("Old Stock Quantity: " + IngredientSet.getEntry(i).getStockQuantity() + "");
                    System.out.print("Enter New Stock Quantity: ");
                    editQuantity = scanQuantity.nextInt();
                    
                    IngredientSet.getEntry(i).setStockQuantity(editQuantity);
                    System.out.println("This stock successfully changed!");
                }
                else if (select == 3) {
                    System.out.println("Old Stock Lowest Quantity: " + IngredientSet.getEntry(i).getLowestQuantity() + "");
                    System.out.print("Enter New Stock Lowest Quantity: ");
                    editLowQuantity = scanLowQuantity.nextInt();
                    
                    IngredientSet.getEntry(i).setLowestQuantity(editLowQuantity);
                    System.out.println("This stock successfully changed!");
                }
                else if (select == 4) {
                    System.out.println("Old Stock Lowest Allow: " + IngredientSet.getEntry(i).getLowestAllowed() + "");
                    System.out.print("Enter New Stock Lowest Allow: ");
                    editLowAllow = scanLowAllow.nextInt();
                    
                    IngredientSet.getEntry(i).setLowestAllowed(editLowAllow);
                    System.out.println("This stock successfully changed!");
                }
                else{
                    ingredientMenu();
                }
                
    
            }else
                {
                    ingredientMenu();
                }
            }
        }
        
    }
   
   public void deleteStock() {
        
        String stockID;
        char confirm;
        //Item delete = null;
        Scanner scan = new Scanner(System.in);
        
        
        System.out.print("Enter stockID to delete the stock: ");
        stockID = scan.nextLine();
        
        
        for(int i = 1; i <= IngredientSet.getNum(); i++) {
            
        if(IngredientSet.getEntry(i).getStockId().equals(stockID)){
        System.out.println("----------------- Delete Item -----------------");
        System.out.println(IngredientSet.getEntry(i).toString() + "");
        System.out.println("------------------------------------------");
        System.out.print("Do you confirm delete this item? (Y/N): ");
        confirm = scan.nextLine().charAt(0);
        
        if(confirm == 'Y' || confirm == 'y'){
            IngredientSet.delete(i);
            System.out.println("This item have been deleted!");
        }
        else{
            ingredientMenu();
        }
        }
        //else{
           // System.out.print("Invalid Item ID! Please re-enter: ");
           // scan.next();
        //}
        }
   }
   
   public static void displayStock() {
        System.out.println("-------------- Total "+IngredientSet.getNum()+" Stock --------------");
        System.out.println("------------------------------------------------");
        if (!IngredientSet.isEmpty()) {
        for(int i = 1; i <= IngredientSet.getNum(); i++){
            System.out.println(IngredientSet.getEntry(i).toString());
        
        }
        }else
            System.out.println("The Stock List is Empty!");
    }
   
   
    
}
