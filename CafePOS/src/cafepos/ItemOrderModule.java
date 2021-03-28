/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import Entity.ItemOrder;
import Entity.Item;
import ADT.ArrayList;
import static cafepos.ItemModule.ItemSet;
import java.util.Scanner;

/**
 *
 * @author Wen
 */
public class ItemOrderModule {

    public ArrayList<ItemOrder> OrderList = new ArrayList<ItemOrder>();
    int itemNum = 0;
    double total;
    Scanner sc = new Scanner(System.in);

    // create the item list for the order 
    public boolean createOrderList() {
        boolean created = false;
        boolean found = false;
        int count = 0;
        char nextItem;
        do {
            System.out.println("-----------Menu-------------");
            ItemModule.displayItem();
            System.out.println("Please enter the item id (exp: x0000): ");
            while (!sc.hasNext("[A-Za-z]\\d{4}")) {
                System.out.println("The item id is wrong. \nPlease enter again: ");
                sc.nextLine();
            }
            String itemId = sc.nextLine().toUpperCase();

            Item itemsOrder = null;
            for (int i = 1; i <= ItemSet.getNum(); i++) {

                // retrieve the existing item menu
                if (ItemSet.getEntry(i).getItemId().equals(itemId)) {
                    itemsOrder = ItemSet.getEntry(i);
                    found = true;
                    break;
                } else {
                    found = false;
                }
            }
            if (found) {
                System.out.print("Quantity: ");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                int qty = sc.nextInt();
                sc.nextLine();
                count++;
                ItemOrder it = new ItemOrder((itemNum + count), itemsOrder, qty);
                created = OrderList.add(it);
                total += it.getSubTotal();

                System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                System.out.print("\n" + OrderList);
                System.out.println("----------------------------");
                System.out.println("Total : RM" + total);
            } else {
                System.out.println("Not found.");
            }

            System.out.print("More item?");
            nextItem = sc.nextLine().charAt(0);
            nextItem = Character.toUpperCase(nextItem);
        } while (nextItem == 'Y');

        return created;
    }

    // display items added to the order
    public void displayList() {
        System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
        System.out.print(OrderList);
    }

    public ArrayList<ItemOrder> getItemOrderList() {
        return this.OrderList;
    }

    public double getTotal() {
        return total;
    }

}
