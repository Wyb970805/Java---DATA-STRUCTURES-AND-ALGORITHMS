/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cafepos;

import ADT.*;
import Entity.*;
import ADT.Queue.*;
import static cafepos.CafePOS.mainMenu;
import static cafepos.ItemModule.ItemSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Wen
 */
public class OrderModule {

    char exit = 'a';

    int orderNum = 1003, tbNum = 0;
    String orderList = null;
    Scanner sc = new Scanner(System.in);
    static QueueWithIteratorInterface<Order> orderLine = new CircularCounterQueueWithIterator<>();
    static QueueWithIteratorInterface<Order> completedOrder = new CircularCounterQueueWithIterator<>();
    //ItemOrderModule itemMenu = new ItemOrderModule();

    /*
    // initialize existed order
    Order a = new Order(1001, 'T', 0);
    Order b = new Order(1002, 'T', 0);
    Order c = new Order(1003, 'T', 0);
    Order d = new Order(1004, 'T', 0);
     */
    public void orderMenu() {
        //a.setOrderList();
        /* orderLine.addToQueue(a);
        orderLine.addToQueue(b);
        orderLine.addToQueue(c);
        orderLine.addToQueue(d);
         */
        int choice;

        do {
            //System.out.println("Hi, welcome!");
            System.out.println("----------------------------");
            System.out.println("-------=Order Module=-------");
            System.out.println("----------------------------");
            System.out.println("1. Place Order");
            System.out.println("2. Update Status");
            System.out.println("3. Modify and Requeue Order");
            System.out.println("4. Remove Order");
            System.out.println("5. Show all order");
            System.out.println("0. Exit");
            System.out.printf("Please enter the index (1-5): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number! \nPlease enter again: ");
                sc.next(); // this is important!
            }
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
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
                    mainMenu();
                    break;
                default:
                    System.out.println("Error! Please select between 1 - 5!");

            }
        } while (choice != 0);
    }

    public void placeOrder() {
        boolean error = false;
        System.out.println("New Order (Y/N)?");
        char order = sc.nextLine().charAt(0);

        while (!Character.isLetter(order)) {
            System.out.println("Please enter yes (Y) or no (n): ");
            order = sc.nextLine().charAt(0);
        }

        while (order == 'Y' || order == 'y') {

            //    System.out.print("Member ID: ");
            //    String mid = sc.nextLine();
            do {
                System.out.print("Dine in - D, Take Away - T (q to exit): ");
                char orderType = sc.nextLine().charAt(0);
                orderType = Character.toUpperCase(orderType);

                if (orderType == 'D' || orderType == 'T') {

                    if (orderType == 'D') {

                        do {
                            System.out.print("Table Number (1-20): ");
                            while (!sc.hasNextInt()) {
                                System.out.println("That's not a number! \nPlease enter again: ");
                                sc.next(); // this is important!
                            }
                            tbNum = sc.nextInt();
                            sc.nextLine();
                        } while (tbNum < 1 || tbNum > 20);

                        /*tbNum = sc.nextInt();
                        sc.nextLine();
                         */
                    } else {
                        tbNum = 0;
                    }
                    orderNum++;

                    ItemOrderModule itemMenu = new ItemOrderModule();

                    boolean created = itemMenu.createOrderList();
                    if (created) {

                        Order od = new Order(orderNum/*, itemMenu.getItemOrderList()*/, orderType, tbNum);
                        od.setOrderList(itemMenu.getItemOrderList());
                        od.setTotalPrice(itemMenu.getTotal());
                        boolean added = orderLine.addToQueue(od);
                        if (added == true) {
                            System.out.println("Order created.");
                            System.out.println(od);
                            System.out.println("----------------------------");
                        } else {
                            System.out.println("ERROR! Order haven't created.");
                        }
                    } else {
                        System.out.println("Error");
                    }
                } else if (orderType == 'Q') {
                    System.out.println("Ok. Will go back.");
                    break;
                } else {
                    System.out.println("Please enter D or T. q to exit.");
                    error = true;
                }
            } while (error == true);

            System.out.println("Next Order (Y/N)?");
            order = sc.nextLine().charAt(0);
            while (!Character.isLetter(order)) {
                System.out.println("Please enter yes (Y) or no (N): ");
                order = sc.nextLine().charAt(0);
            }
        } //System.out.println("Show first order");
        //System.out.println(orderLine.getFirst());

    }

    public void removeOrder() {
        Order removed = null;
        System.out.println("Are you sure you want to cancel the first order in queue (Y/N)?");
        char confirm = sc.nextLine().charAt(0);
        while (!Character.isLetter(confirm)) {
            System.out.println("Please enter yes (Y) or no (N): ");
            confirm = sc.nextLine().charAt(0);
        }
        if (confirm == 'Y' || confirm == 'y') {
            if (!orderLine.isEmpty()) {
                System.out.println(orderLine.getFirst());
                System.out.println("Are you sure you want to cancel this order(Y - Yes /other to cancel)?");
                char con = sc.nextLine().charAt(0);
                if (con == 'Y' || con == 'y') {
                    removed = orderLine.removeFQueue();
                }
                System.out.println(removed + "------ Already removed.");
            } else {
                System.out.println("There is no order in queue.");
            }

        }

    }

    public void updateOrderStatus() {
        if (!orderLine.isEmpty()) {
            System.out.println("\nUpdate the Order to the completed status.");
            System.out.println("------------------------");
            Order completed = orderLine.removeFQueue();
            System.out.println(completed);
            System.out.println("------------------------");
            completedOrder.addToQueue(completed);

            System.out.println("Your order is completed.");
        } else {
            System.out.println("------------------------");
            System.out.println("There is no order in queue.");
        }

    }

    public void editOrder() {
        if (!orderLine.isEmpty()) {
            System.out.println("\nAny changes of the order on? ");
            System.out.println("----------------------------");
            System.out.println("1. Ordered Item");
            System.out.println("2. Member ID");
            System.out.println("3. Table Number");
            System.out.println("4. Order Type");
            System.out.println("0. Back to preious");
            System.out.printf("Please enter the index (1-4): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number! \nPlease enter again: ");
                sc.next(); // this is important!
            }
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    editItem();
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
                case 0:
                    break;
                default:
                    System.out.println("Only 1 - 4 are offered to change.");
            }

        }
    }

    public void editItem() {
        System.out.println("\n--------Modify Item---------");
        System.out.println("----------------------------");
        System.out.println("1. Add Order item");
        System.out.println("2. Modify Order item");
        System.out.println("3. Remove Order item");
        System.out.println("0. Exit");
        System.out.printf("Please enter the index (1-4): ");
        while (!sc.hasNextInt()) {
            System.out.println("That's not a number! \nPlease enter again: ");
            sc.next(); // this is important!
        }
        int choice = sc.nextInt();
        double oldTotal;
        double total = 0;
        double addPayment;
        sc.nextLine();
        String itemId;
        Item itemsOrder;
        int num;
        boolean found = false;
        char more = 'n';
        switch (choice) {
            case 1:

                System.out.println(orderLine.getFirst().getOrderList());
                oldTotal = orderLine.getFirst().getTotalPrice();
                System.out.println("Current Total: " + oldTotal);
                System.out.println("-----------Menu-------------");
                ItemModule.displayItem();
                do {
                    System.out.println("Please enter the item id (exp: x0000): ");
                    while (!sc.hasNext("[A-Za-z]\\d{4}")) {
                        System.out.println("The item id is wrong. \nPlease enter again: ");
                        sc.nextLine();
                    }
                    itemId = sc.nextLine().toUpperCase();

                    itemsOrder = null;
                    for (int i = 1; i <= ItemSet.getNum(); i++) {

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
                        int lastIndex = orderLine.getFirst().getOrderList().getLength();
                        lastIndex++;
                        ItemOrder newEntry = new ItemOrder(lastIndex, itemsOrder, qty);
                        orderLine.getFirst().getOrderList().add(newEntry);

                        System.out.println("More item to add (Y/other)?");
                        more = sc.nextLine().charAt(0);
                        while (!Character.isLetter(more)) {
                            System.out.println("Please enter yes (Y) or no (n): ");
                            more = sc.nextLine().charAt(0);
                        }

                    } else {
                        System.out.println("Not found.");
                    }
                } while (more == 'y' || more == 'Y');

                for (int i = 1; i <= orderLine.getFirst().getOrderList().getLength(); i++) {
                    total += orderLine.getFirst().getOrderList().getEntry(i).getSubTotal();
                }
                addPayment = total - oldTotal;
                orderLine.getFirst().setTotalPrice(total);

                System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                System.out.print("\n" + orderLine.getFirst().getOrderList());
                System.out.println("----------------------------");
                System.out.println("Total : RM" + orderLine.getFirst().getTotalPrice()); // total is wrong - recalculate subtotal

                System.out.println("Additional payment: " + addPayment);
                break;
            case 2:
                System.out.println(orderLine.getFirst().getOrderList());
                oldTotal = orderLine.getFirst().getTotalPrice();
                System.out.println("Current Total: " + oldTotal);
                System.out.println("Which Item you want to modify? \nPlease enter the index (1):");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                num = sc.nextInt();
                sc.nextLine();
                System.out.println(orderLine.getFirst().getOrderList().getEntry(num));
                System.out.println("-----------Menu-------------");
                ItemModule.displayItem();

                System.out.println("Please enter the item id (exp: x0000): ");
                while (!sc.hasNext("[A-Za-z]\\d{4}")) {
                    System.out.println("The item id is wrong. \nPlease enter again: ");
                    sc.nextLine();
                }
                itemId = sc.nextLine().toUpperCase();

                itemsOrder = null;
                for (int i = 1; i <= ItemSet.getNum(); i++) {

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

                    orderLine.getFirst().getOrderList().getEntry(num).setItemOrder(itemsOrder);
                    orderLine.getFirst().getOrderList().getEntry(num).setItemQty(qty);
                    double newSub = orderLine.getFirst().getOrderList().getEntry(num).calculateSubTotal(qty);
                    orderLine.getFirst().getOrderList().getEntry(num).setSubTotal(newSub);

                    for (int i = 1; i <= orderLine.getFirst().getOrderList().getLength(); i++) {
                        total += orderLine.getFirst().getOrderList().getEntry(i).getSubTotal();
                    }
                    addPayment = total - oldTotal;
                    orderLine.getFirst().setTotalPrice(total);

                    System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                    System.out.print("\n" + orderLine.getFirst().getOrderList());
                    System.out.println("----------------------------");
                    System.out.println("Total : RM" + orderLine.getFirst().getTotalPrice()); // total is wrong - recalculate subtotal

                    System.out.println("Additional payment: " + addPayment);

                } else {
                    System.out.println("Not found.");
                }

                break;
            case 3:
                System.out.println(orderLine.getFirst().getOrderList());
                oldTotal = orderLine.getFirst().getTotalPrice();
                System.out.println("Current Total: " + oldTotal);
                System.out.println("Which Item you want to modify? \nPlease enter the index (1):");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                num = sc.nextInt();
                sc.nextLine();
                System.out.print("Are you sure to remove (Y/other)? ");
                char conf = sc.nextLine().charAt(0);
                while (!Character.isLetter(conf)) {
                    System.out.println("Please enter an alphabel: ");
                    conf = sc.nextLine().charAt(0);
                }
                if (conf == 'Y' || conf == 'y') {
                    orderLine.getFirst().getOrderList().remove(num);
                } else {
                    System.out.println("The items is not remove yet.");
                }
                for (int i = 1; i <= orderLine.getFirst().getOrderList().getLength(); i++) {
                    orderLine.getFirst().getOrderList().getEntry(i).setItemNum(i);
                    total += orderLine.getFirst().getOrderList().getEntry(i).getSubTotal();
                }
                addPayment = total - oldTotal;
                orderLine.getFirst().setTotalPrice(total);

                System.out.printf("%-3s  %-6s %-20s %3s %8s", "No.", "Item(s)", "Name", "Qty", "Price(RM)");
                System.out.print("\n" + orderLine.getFirst().getOrderList());
                System.out.println("----------------------------");
                System.out.println("Total : RM" + orderLine.getFirst().getTotalPrice()); // total is wrong - recalculate subtotal

                System.out.println("Additional payment: " + addPayment);

                break;
            case 0:
                break;
            default:
                System.out.println("Error! Please select between 1 - 4!");

        }

    }

    public void editTableNum() {
        boolean error = false;
        if (!orderLine.isEmpty()) {
            do {
                Order first = orderLine.getFirst();
                System.out.println("Current table number = " + first.getTableNo());
                System.out.print("New table 1-20 (0 to cancel): ");
                while (!sc.hasNextInt()) {
                    System.out.println("That's not a number! \nPlease enter again: ");
                    sc.next(); // this is important!
                }
                int newTable = sc.nextInt();
                sc.nextLine();
                if (newTable >= 1 && newTable <= 20) {
                    orderLine.getFirst().setTableNo(newTable);
                    System.out.println("Your changes is done.");
                    System.out.println("----------------------------");
                    System.out.println(orderLine.getFirst());
                    System.out.println("----------------------------");
                } else if (newTable == 0) {
                    break;
                } else {
                    System.out.println("Please enter table number between 01-20.");
                    error = true;
                }

            } while (error == true);
        } else {
            System.out.println("No order in queue.");

        }

    }

    public void editOrderType() {
        boolean error = false;
        if (!orderLine.isEmpty()) {
            do {
                Order first = orderLine.getFirst();
                System.out.println("Current Order Type = " + first.getOrderType());
                System.out.println("Change to Dine in (D) or Take away (T) (q to cancel): ");
                char newOType = sc.nextLine().charAt(0);
                newOType = Character.toUpperCase(newOType);
                if (newOType == 'D' || newOType == 'T') {
                    orderLine.getFirst().setOrderType(newOType);
                    if (newOType == 'T') {
                        orderLine.getFirst().setTableNo(0);
                    } else {
                        editTableNum();
                    }
                    System.out.println("----------------------------");
                    System.out.println(orderLine.getFirst());
                    System.out.println("----------------------------");
                } else if (newOType == 'q') {
                    break;
                } else {
                    System.out.println("Please enter D for Dine in or T for Take Away.");
                    error = true;
                }

            } while (error == true);

        } else {
            System.out.println("No order in queue.");

        }
        //return changed;
    }

    public void showOrder() {
        int choice = 0;
        do {
            System.out.println("\n---------Show Order--------");
            System.out.println("1. Show Order Queue");
            System.out.println("2. Show Completed Order");
            System.out.println("0. Back to preious");
            System.out.println("----------------------------");
            System.out.printf("Please enter index (1-2): ");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number!\n Please enter again: ");
                sc.next(); // this is important!
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showOrderQueue();
                    break;
                case 2:
                    showCompletedQueue();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please select 1 or 2, 0 to back.");
            }
        } while (choice != 0);
    }

    private void showOrderQueue() {
        if (!orderLine.isEmpty()) {
            System.out.println("\n-------Show All Order-------");
            System.out.println("----------------------------");
            Iterator<Order> iter = orderLine.getIterator();
            while (iter.hasNext()) {
                Order o = iter.next();
                System.out.printf(o.getOrder().toString());
            }
        } else {
            System.out.println("There is no order in queue");
        }
    }

    private void showCompletedQueue() {
        if (!completedOrder.isEmpty()) {
            System.out.println("\n--Show All Completed Order--");
            System.out.println("----------------------------");
            Iterator<Order> iter = completedOrder.getIterator();
            while (iter.hasNext()) {
                Order o = iter.next();
                System.out.printf(o.getOrder().toString());
            }
        } else {
            System.out.println("There is no completed order.");
        }
    }

}
