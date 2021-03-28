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
       IngredientSet.add(new Ingredient("S1001","Fish",32,10));
       IngredientSet.add(new Ingredient("S1002","Chicken",32,5));
       
       int select;
       do{
           System.out.println("\n ------------------------------------ ");
           System.out.println("|----------- Stock Module -----------|");
           System.out.println("|------------------------------------|");
           System.out.println("|  1. Add new Ingredient             |");
           System.out.println("|  2. Edit Ingredient                |");
           System.out.println("|  3. Delete Ingredient              |");
           System.out.println("|  4. Display All Ingredients        |");
           System.out.println("|  0. Exit                           |");
           System.out.println(" ------------------------------------ ");
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
                mainMenu();
                break;
            default:
                System.out.println("Invalid number! Please re-enter: ");            
        }
        }while(select != 0 );
    }

   public void addStock() {
        
        String stockId, stockName;
        int stockQuantity, stockLowQuantity;
        int select;
        char category = 0;
        char confirm = 0;
        
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter Ingredient Id: ");
        stockId = scan.nextLine();
        
        System.out.print("Enter Ingredient Name: ");
        stockName = scan.nextLine();
        
        System.out.print("Enter Ingredient Quantity: ");
        stockQuantity = scan.nextInt();
        
        System.out.print("Enter Ingredient LowestQuanity: ");
        stockLowQuantity = scan.nextInt();
       
        IngredientSet.add(new Ingredient(stockId, stockName, stockQuantity, stockLowQuantity));
        System.out.println("Ingredient Added Successfully!");
        
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
        
        System.out.print("Enter IngredientID to modify stock: ");
        stockID = scan.nextLine();
        
        for(int i = 1; i <= IngredientSet.getNum(); i++) {
            
            if(IngredientSet.getEntry(i).getStockId().equals(stockID)) {
                System.out.println("\n------------------ Edit Ingredient --------------------");
                System.out.print(IngredientSet.getEntry(i).toString());
                System.out.println("-------------------------------------------------------");
                System.out.print("Do you confirm edit this ingredient? (Y/N): ");
                confirm = scan.nextLine().charAt(0);
                if(confirm == 'Y' || confirm == 'y'){
                System.out.println("\n ------------------------------------- ");
                System.out.println("|------------- Edit Stock ------------|");
                System.out.println("|-------------------------------------|");
                System.out.println("|  1. Edit Ingredient Name            |");
                System.out.println("|  2. Edit Ingredient Quantity        |");
                System.out.println("|  3. Edit Ingredient Lowest Quantity |");
                System.out.println("|  0. Exit:                           |");
                System.out.println(" ------------------------------------- ");
                System.out.print("Enter number (1-3): ");
                select = scan.nextInt();

                if (select == 1) {
                    
                    System.out.println("Old Ingredient Name: " + IngredientSet.getEntry(i).getStockName() + "");//.toString()
                    System.out.print("Enter New Ingredient Name: ");
                    editName = scanName.nextLine();
                    
                    IngredientSet.getEntry(i).setStockName(editName);
                    System.out.println("This Ingredient successfully changed!");
                }
                else if (select == 2) {
                    System.out.println("Old Ingredient Quantity: " + IngredientSet.getEntry(i).getStockQuantity() + "");
                    System.out.print("Enter New Ingredient Quantity: ");
                    editQuantity = scanQuantity.nextInt();
                    
                    IngredientSet.getEntry(i).setStockQuantity(editQuantity);
                    System.out.println("This Ingredient successfully changed!");
                }
                else if (select == 3) {
                    System.out.println("Old Ingredient Lowest Quantity: " + IngredientSet.getEntry(i).getLowestQuantity() + "");
                    System.out.print("Enter New Ingredient Lowest Quantity: ");
                    editLowQuantity = scanLowQuantity.nextInt();
                    
                    IngredientSet.getEntry(i).setLowestQuantity(editLowQuantity);
                    System.out.println("This Ingredient successfully changed!");
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
        
        
        System.out.print("Enter IngredientID to delete the stock: ");
        stockID = scan.nextLine();
        
        
        for(int i = 1; i <= IngredientSet.getNum(); i++) {
            
        if(IngredientSet.getEntry(i).getStockId().equals(stockID)){
        System.out.println("\n-------------- Delete Ingredient --------------");
        System.out.print(IngredientSet.getEntry(i).toString() + "");
        System.out.println("-----------------------------------------------");
        System.out.print("Do you confirm delete this ingredient? (Y/N): ");
        confirm = scan.nextLine().charAt(0);
        
        if(confirm == 'Y' || confirm == 'y'){
            IngredientSet.remove(i);
            System.out.println("This Ingredient have been deleted!");
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
       System.out.println("\n ------------------------------------");
        System.out.println("|---------- Total " +IngredientSet.getNum()+ " Stock -----------|");
        System.out.println(" ------------------------------------");
        if (!IngredientSet.isEmpty()) {
        for(int i = 1; i <= IngredientSet.getNum(); i++){
            System.out.println(IngredientSet.getEntry(i).toString());
        
        }
        }else
            System.out.println("The Stock List is Empty!");
    }
   
   
    
}
